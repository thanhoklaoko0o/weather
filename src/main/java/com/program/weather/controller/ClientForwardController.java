package com.program.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.program.weather.entity.UserEntity;
import com.program.weather.service.impl.UserServiceImpl;
import com.program.weather.utils.Constants;
/**
 * 
 * @author NgocHung
 *
 */
@Controller
public class ClientForwardController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	/**
	 * Load pageLogin when user access to APP
	 * 
	 * @return
	 */
	@GetMapping(value = { "/", "/login"})
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
	public String page404() {return "error/401";}
}
