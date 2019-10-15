package com.program.weather.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.program.weather.converter.UserConverter;
import com.program.weather.dto.UserDTO;
import com.program.weather.entity.UserEntity;
import com.program.weather.service.impl.UserServiceImpl;
import com.program.weather.utils.Constants;

@Controller
public class ForwardController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private UserConverter userConverter;

	/**
	 * Load pageLogin when user access to APP
	 * 
	 * @return
	 */
	@GetMapping(value = { "/", "/login" })
	public String login() {

		return "user/pageLogin";
	}

	/**
	 * Logout account on APP
	 * 
	 * @return
	 */
	@GetMapping("/logoutSuccessful")
	public String logout() {

		return "redirect:login?logoutTC";
	}

	/**
	 * process request URL
	 * 
	 * @return
	 */
	@GetMapping("/processURL")
	public String processURL() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String URL = urlMappingUser(authentication);

		return URL;
	}

	/**
	 * URL request <-> role
	 * 
	 * @param authentication
	 * @return URL <-> Role
	 */
	public String urlMappingUser(Authentication authentication) {

		String USER  = "ROLE_USER";
		String ADMIN = "ROLE_ADMIN";
		String url   = "";
		List<GrantedAuthority> authorities = getListAuthority(authentication);

		if (checkRoleUser(authorities, USER))

			url = "redirect:/home-weather";

		if (checkRoleUser(authorities, ADMIN))

			url = "redirect:/home-admin";

		return url;
	}

	/**
	 * Check roleName with list contain authority of USER
	 * 
	 * @param userAuthority
	 * @param role
	 * @return if roleName in userAuthority then return True
	 */
	public boolean checkRoleUser(List<GrantedAuthority> userAuthority, String roleName) {

		return userAuthority.stream().anyMatch(author -> author.getAuthority().equalsIgnoreCase(roleName));
	}

	/**
	 * get list authority
	 * 
	 * @param authentication
	 * @return List contain authority
	 */
	public List<GrantedAuthority> getListAuthority(Authentication authentication) {

		List<GrantedAuthority> userAuthority = new ArrayList<GrantedAuthority>();

		@SuppressWarnings("unchecked")
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
		userAuthority.addAll(authorities);

		return userAuthority;
	}

	/**
	 * show page register for USER
	 * 
	 * @param model
	 * @return page register
	 */
	@GetMapping("/registerAccount")
	public String pageRegister(Model model) {
		UserDTO userDTO = new UserDTO();
		model.addAttribute("userDTO", userDTO );

		return "user/register";
	}

	/**
	 * process info User when User register
	 * 
	 * @param userEntity
	 * @param result
	 * @return
	 */
	@PostMapping("/registerAccount")
	public String processRegister(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result) {

		// check error validate when user register
		if (userServiceImpl.checkExistsByUserName(userDTO.getUserName())) {

			return "redirect:registerAccount?msgUserName=Register_Failed";
		}

		if (userServiceImpl.checkExistsByEmail(userDTO.getEmail())) {

			return "redirect:registerAccount?msgEmail=Register_Failed";
		}

		if (result.hasErrors()) {

			return "user/register";
		}

		userServiceImpl.saveUser(userConverter.convertUserEntity(userDTO));

		return "redirect:login?message=Register_Successful";
	}

	/**
	 * Check exists username in DB
	 * 
	 * @param userName
	 * @return
	 */
	@GetMapping("/checkUserName")
	@ResponseBody
	public String checkExistsByUserName(@RequestParam String userName) {

		Boolean result = userServiceImpl.checkExistsByUserName(userName);

		return "" + result;
	}

	/**
	 * Check exists email in DB
	 * 
	 * @param email
	 * @return
	 */
	@GetMapping("/checkEmail")
	@ResponseBody
	public String checkExistsByEmail(@RequestParam String email) {

		Boolean result = userServiceImpl.checkExistsByEmail(email);

		return "" + result;
	}

	@PostMapping("/processUrlFail")
	public String processUrlFail(@RequestParam String username, @RequestParam String password) {

		if (checkUserExist(username)) {

			UserEntity userEntity = userServiceImpl.findByUserName(username);

			if (userEntity.isEnabled() == Constants.UN_ACTIVE)

				return "redirect:error/401";

			else

				return "redirect:login?error=true";
		}

		return "redirect:login?error=true";
	}

	public boolean checkUserExist(String userName) {

		return userServiceImpl.checkExistsByUserName(userName);
	}

	@GetMapping("/error/401")
	public String page404() {

		return "error/401";
	}

	@GetMapping("/getNewPassWord")
	@ResponseBody
	public String getNewPassword(@RequestParam String name) {
		// Create a Simple MailMessage.
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(name);
		message.setSubject("Test Simple Email");
		message.setText("Hello, Im testing Simple Email");

		// Send Message!
		this.emailSender.send(message);

		return "";
	}

}
