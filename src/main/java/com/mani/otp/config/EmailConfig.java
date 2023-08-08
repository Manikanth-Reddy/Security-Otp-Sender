package com.mani.otp.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

//	@Value("${spring.mail.host}")
//	private String mailhost;
//	@Value("${spring.mail.port}")
//	private String mailport;
//	@Value("${spring.mail.username")
//	private String username;
//	@Value("${spring.mail.password}")
//	private String password;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(587);
		javaMailSender.setUsername("ravan.redde@gmail.com");
		javaMailSender.setPassword("Manireddy#4289");
		Properties properties = javaMailSender.getJavaMailProperties();
	    properties.put("mail.smtp.starttls.enable", "true");
	    javaMailSender.setJavaMailProperties(properties);
		return javaMailSender;
	}
}
