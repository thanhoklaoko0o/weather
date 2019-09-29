package com.program.weather.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {

	/**
	 * Encode with BCryptPasswordEncoder when save user
	 * 
	 * @param password
	 * @return
	 */
	public static String encrytePassword(String password) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);

	}
}
