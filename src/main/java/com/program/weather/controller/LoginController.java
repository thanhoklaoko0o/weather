package com.program.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
		
		@GetMapping(value= {"/","/login"})
		public String login() {
			
			return "pageLogin";
		}
		
		@GetMapping("/logoutSuccessful")
		public String logout() {
			return "redirect:login?logoutTC";
		}
}
