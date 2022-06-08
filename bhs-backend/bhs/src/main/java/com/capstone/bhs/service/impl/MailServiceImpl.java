package com.capstone.bhs.service.impl;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.capstone.bhs.common.CommonConstant;
import com.capstone.bhs.config.ApplicationProperties;
import com.capstone.bhs.model.entity.AdUser;
import com.capstone.bhs.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private ApplicationProperties applicationProperties;

	@Async
	public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
			message.setTo(to);
			message.setFrom(applicationProperties.getMail().getFrom());
			message.setSubject(subject);
			message.setText(content, true);
			javaMailSender.send(mimeMessage);
		} catch (MailException | MessagingException e) {
			e.printStackTrace();
		}
	}

	@Async
	public void sendEmailFromTemplate(AdUser user, String content, String title) {
		String subject = title;
		sendEmail(user.getUsername(), subject, content, false, true);
	}

	@Async
	public void sendActivationEmail(AdUser user) {
		StringBuilder activeLink = new StringBuilder("");
		activeLink.append(applicationProperties.getMail().getBaseUrl());
		activeLink.append("/active/").append(user.getActiveKey());
		String content = CommonConstant.Email.buildActivationEmail(activeLink.toString());
		sendEmailFromTemplate(user, content, "[BHS] Account activation");
	}

	@Async
	public void sendPasswordResetMail(AdUser user) {
		StringBuilder resetLink = new StringBuilder("");
		resetLink.append(applicationProperties.getMail().getBaseUrl());
		resetLink.append("/reset-password/finish/");
		resetLink.append(user.getResetKey());
		String content = CommonConstant.Email.buildPasswordResetEmail(resetLink.toString());
		sendEmailFromTemplate(user, content, "[BHS] Password reset");
	}
}
