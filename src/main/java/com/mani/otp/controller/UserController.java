package com.mani.otp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mani.otp.dto.LoginDto;
import com.mani.otp.dto.RegisterDto;
import com.mani.otp.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerdto){
		return new ResponseEntity<>(userService.register(registerdto),HttpStatus.OK);
	}

	@PutMapping("/very-account")
	public ResponseEntity<String> verifyAccount(@RequestParam("email") String email ,@RequestParam String otp){
		return new ResponseEntity<>(userService.verifyAccount(email,otp),HttpStatus.OK);
	}
	@PutMapping("/regenerate-otp")
	public ResponseEntity<String> regenerateOtp(@RequestParam String email){
		return new ResponseEntity<>(userService.regenerateotp(email),HttpStatus.OK);
	}
	@PutMapping("/login")
	public void login(@RequestBody LoginDto loginDto) {
		new ResponseEntity<>(userService.login(loginDto),HttpStatus.OK);
	}
}
