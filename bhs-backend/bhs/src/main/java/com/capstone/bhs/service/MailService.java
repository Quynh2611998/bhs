package com.capstone.bhs.service;

import org.springframework.stereotype.Service;

import com.capstone.bhs.model.entity.AdUser;

@Service
public interface MailService {
	public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);

	public void sendEmailFromTemplate(AdUser user, String templateName, String titleKey);

	public void sendActivationEmail(AdUser user);

	public void sendPasswordResetMail(AdUser user);
}
