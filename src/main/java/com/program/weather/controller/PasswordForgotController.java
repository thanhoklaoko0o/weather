package com.program.weather.controller;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.program.weather.dto.tranfer.MailDTO;
import com.program.weather.entity.PasswordResetToken;
import com.program.weather.entity.UserEntity;
import com.program.weather.repository.PasswordResetTokenRepository;
import com.program.weather.service.UserService;
import com.program.weather.service.impl.EmailService;

@Controller
public class PasswordForgotController {

	@Autowired 
	private UserService userService;

	@Autowired 
	private PasswordResetTokenRepository tokenRepository;

	@Autowired 
	private EmailService emailService;

	@PostMapping("/forgot-password")
	@ResponseBody
	public Boolean processForgotPasswordForm(@RequestParam String email, HttpServletRequest request) {
		UserEntity user = userService.findByEmail(email);
		if (user == null){
			return false;
		}
		
		PasswordResetToken token = new PasswordResetToken();
		token.setToken(UUID.randomUUID().toString());
		token.setUser(user);
		token.setExpiryDate(30);
		tokenRepository.save(token);
		
		MailDTO mail = new MailDTO();
		mail.setFrom("no-reply@weatherapp.com");
		mail.setTo(user.getEmail());
		mail.setSubject("Password reset request");
		
		Map<String, Object> model = new HashMap<>();
		model.put("token", token);
		model.put("user", user);
		model.put("signature", "WeatherApp.com");
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
		mail.setModel(model);
		emailService.sendEmail(mail);
		
		return true;
	}
}
