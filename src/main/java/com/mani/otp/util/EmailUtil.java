package com.mani.otp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendOtpToMail(String email, String otp) throws MessagingException {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Verify OTP");
		message.setText("Hi Namaste your OTP is :"+otp);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		messageHelper.setTo(email);
		messageHelper.setSubject("Verify OTP");
		messageHelper.setText("""
				<div>
				<a href="http://localhost:8080/verify-account?email=%s&otp=%s" target="_blank">Click Here To verify </a>
				</div>
				""".formatted(email,otp));
		javaMailSender.send(mimeMessage);
		
	}
}
