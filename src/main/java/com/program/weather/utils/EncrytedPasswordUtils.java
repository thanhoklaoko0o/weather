package com.program.weather.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {
	
	 // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
 
	
	/*
	 * // lay ma hoa mat khau insert vao db public static void main(String[] args) {
	 * String password = "ngochung"; String encrytedPassword =
	 * encrytePassword(password);
	 * 
	 * 
	 * System.out.println("Encryted Password: " + encrytedPassword); }
	 */
	 
}
