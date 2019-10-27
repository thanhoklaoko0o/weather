package com.program.weather.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

		return "email/resetPassword";
	}

	@PostMapping
	@Transactional
	public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDTO form,
										BindingResult result,
										RedirectAttributes redirectAttributes) {

		if (result.hasErrors()){
			redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
			redirectAttributes.addFlashAttribute("passwordResetForm", form);
			return "redirect:/reset-password?token=" + form.getToken();
		}

		PasswordResetToken token = tokenRepository.findByToken(form.getToken());
		UserEntity user = token.getUser();
		String updatedPassword = passwordEncoder.encode(form.getPassword());
		userService.updatePassword(updatedPassword, user.getUserId());
		tokenRepository.delete(token);

		return "redirect:/login?resetSuccess";
	}
}
