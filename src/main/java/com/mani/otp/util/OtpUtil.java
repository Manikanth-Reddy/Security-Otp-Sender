package com.mani.otp.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class OtpUtil {

	public String generateOtp() {
		Random random= new Random();
		int i = random.nextInt(999999);
		String output = Integer.toString(i);
		while(output.length()<6) output+="0";
		return output;
	}
}
