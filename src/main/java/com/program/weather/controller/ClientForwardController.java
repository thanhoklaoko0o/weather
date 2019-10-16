package com.program.weather.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.program.weather.entity.UserEntity;
import com.program.weather.service.impl.UserServiceImpl;
import com.program.weather.utils.Constants;

@Controller
public class ClientForwardController {

	@Autowired
	private UserServiceImpl userServiceImpl;

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
}
