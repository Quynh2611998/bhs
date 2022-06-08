package com.capstone.bhs.config;

import java.util.Properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.capstone.bhs.common.CommonConstant;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableConfigurationProperties
public class SpringBootConfiguration {

	private final ApplicationProperties applicationProperties;

	public SpringBootConfiguration(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

	@Bean
	public Docket api() { // -- Swagger UI Configuration
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = applicationProperties.getCors();
		if (!CollectionUtils.isEmpty(config.getAllowedOrigins())
				|| !CollectionUtils.isEmpty(config.getAllowedOriginPatterns())) {
			source.registerCorsConfiguration("/api/**", config);
			source.registerCorsConfiguration("/management/**", config);
			source.registerCorsConfiguration("/v2/api-docs", config);
			source.registerCorsConfiguration("/v3/api-docs", config);
			source.registerCorsConfiguration("/swagger-resources", config);
			source.registerCorsConfiguration("/swagger-ui/**", config);
		}
		return new CorsFilter(source);
	}

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(CommonConstant.Email.EMAIL_USER);
		mailSender.setPassword(CommonConstant.Email.EMAIL_PWD);
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", CommonConstant.Email.PROTOCAL);
		props.put("mail.smtp.auth", CommonConstant.Email.AUTH);
		props.put("mail.smtp.starttls.enable", CommonConstant.Email.START_TLS_ENABLE);
		return mailSender;
	}

}
