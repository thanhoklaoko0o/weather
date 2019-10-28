package com.program.weather.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.program.weather.dto.tranfer.PasswordResetDTO;
import com.program.weather.entity.PasswordResetToken;
import com.program.weather.entity.UserEntity;
import com.program.weather.repository.PasswordResetTokenRepository;
import com.program.weather.service.UserService;

@Controller
@RequestMapping("/reset-password")
public class PasswordResetController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordResetTokenRepository tokenRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping
	public String displayResetPasswordPage(@RequestParam String token, Model model) {
		PasswordResetToken resetToken = tokenRepository.findByToken(token);
		if (resetToken == null){
			model.addAttribute("error", "Could not find password reset token.");
		} else if (resetToken.isExpired()){
			model.addAttribute("error", "Token has expired, please request a new password reset.");
		} else {
			model.addAttribute("token", resetToken.getToken());
		}
		model.addAttribute("passwordResetDTO", new PasswordResetDTO());
		// Title page
		model.addAttribute("pageTitle", "Reset password");
		return "email/resetPassword";
	}

	@PostMapping
	@Transactional
	public String handlePasswordReset(@ModelAttribute("passwordResetDTO") @Valid PasswordResetDTO passwordResetDTO, BindingResult result) {
		// If result has error
		if (result.hasErrors()){
			return "redirect:/reset-password?token=" + passwordResetDTO.getToken();
		}
		// Update password for USER
		PasswordResetToken token = tokenRepository.findByToken(passwordResetDTO.getToken());
		UserEntity user = token.getUser();
		String updatedPassword = passwordEncoder.encode(passwordResetDTO.getPassword());
		userService.updatePassword(updatedPassword, user.getUserId());
		tokenRepository.delete(token);

		return "redirect:/login?resetpassword=true";
	}
}
