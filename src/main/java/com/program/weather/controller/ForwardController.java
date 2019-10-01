package com.program.weather.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.service.impl.UserServiceImpl;
import com.program.weather.utils.Constants;

@Controller
public class ForwardController {
		
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserDetailsService  userDetailsService;
	
	/**
	 * Load pageLogin when user access to APP
	 * @return
	 */
		@GetMapping(value= {"/","/login"})
		public String login() {
			
			return "pageLogin";
		}
		
		/**
		 * Logout account on APP
		 * @return
		 */
		@GetMapping("/logoutSuccessful")
		public String logout() {
			
			return "redirect:login?logoutTC";
		}
		
		@GetMapping("/processURL")
		public String processURL() {
			Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
			String URL = urlMappingUser(authentication);
			
			return URL;
		}
		
		public String urlMappingUser(Authentication authentication) {
			String url = "";
			List<GrantedAuthority> authorities = getListAuthority(authentication);
			
			if(checkRoleUser(authorities, Constants.USER))
				url = "redirect:/home-weather";
			if(checkRoleUser(authorities, Constants.ADMIN))
				url = "redirect:/home-admin";
			
			return url;
		}
		
		public boolean checkRoleUser(List<GrantedAuthority> userAuthority, String role) {
			return userAuthority.stream().anyMatch(author -> author.getAuthority().equalsIgnoreCase(role));
		}
		
		public List<GrantedAuthority> getListAuthority(Authentication authentication){
			List<GrantedAuthority> userAuthority = new ArrayList<GrantedAuthority>();
			@SuppressWarnings("unchecked")
			Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
		    userAuthority.addAll(authorities); 
		    
			return userAuthority;
			
		}
}
