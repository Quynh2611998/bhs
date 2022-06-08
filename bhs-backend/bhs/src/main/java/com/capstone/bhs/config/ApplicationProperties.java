package com.capstone.bhs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import com.capstone.bhs.model.dto.MailDTO;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
	
    private final CorsConfiguration cors = new CorsConfiguration();
    
    private final MailDTO mail = new MailDTO();
    
	public CorsConfiguration getCors() {
		return cors;
	}

	public MailDTO getMail() {
		return mail;
	}
	
}
