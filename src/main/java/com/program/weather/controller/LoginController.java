package com.program.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
		
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
}
