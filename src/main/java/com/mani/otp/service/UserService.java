package com.mani.otp.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mani.otp.dto.LoginDto;
import com.mani.otp.dto.RegisterDto;
import com.mani.otp.entity.User;
import com.mani.otp.repository.UserRepository;
import com.mani.otp.util.EmailUtil;
import com.mani.otp.util.OtpUtil;

import jakarta.mail.MessagingException;

@Service
public class UserService {

	@Autowired
	private OtpUtil util;
	@Autowired
	private EmailUtil emailUtil;
	@Autowired
	private UserRepository repository;

	public String register(RegisterDto registerdto) {
		String otp = util.generateOtp();
		try {
			emailUtil.sendOtpToMail(registerdto.getEmail(), otp);
		} catch (MessagingException e) {
			throw new RuntimeException("Unable to send otp please try again again later");
		}
		User user = new User();
		user.setName(registerdto.getName());
		user.setEmail(registerdto.getEmail());
		user.setPassword(registerdto.getPassword());
		user.setOtp(otp);
		user.setOtpGeneratedType(LocalDateTime.now());
		repository.save(user);
		return "User Registration Succesfull";
	}

	public String verifyAccount(String email, String otp) {
		User user = repository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User Not found with this email :" + email));
		if (user.getOtp().equals(otp)
				&& Duration.between(user.getOtpGeneratedType(), LocalDateTime.now()).getSeconds() < (4 * 60)) {
			user.setActive(true);
			return "OTP Verified You can Login Now";
		} else
			return "Please Generate Otp Again";
	}

	public String regenerateotp(String email) {
		User user = repository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User Not found with this email :" + email));
		String otp = util.generateOtp();
		try {
			emailUtil.sendOtpToMail(email, otp);
		} catch (MessagingException e) {
			throw new RuntimeException("Unable to send otp please try again again later");
		}
		user.setOtp(otp);
		user.setOtpGeneratedType(LocalDateTime.now());
		repository.save(user);
		return "Email Sent....... please very account within 4 minutes";
	}

	public String login(LoginDto loginDto) {
		User user = repository.findByEmail(loginDto.getEmail())
				.orElseThrow(() -> new RuntimeException("User Not found with this email :" + loginDto.getEmail()));
		if(!user.getPassword().equals(loginDto.getPassword())) {
			return "Password is incorrect";
		}
		else if(!user.isActive()){
			return "Your account is not verified";
		}
		
		return "login Succesfull";
	}

}
