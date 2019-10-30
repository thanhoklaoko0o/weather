package com.program.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.program.weather.entity.UserEntity;
import com.program.weather.service.UserService;
import com.program.weather.utils.Constants;
/**
 * 
 * @author NgocHung
 *
 */
@Controller
public class ClientForwardController {

	@Autowired
	private UserService userService;

	/**
	 * Load pageLogin when user access to APP
	 * 
	 * @return
	 */
	@GetMapping(value = { "/", "/login"})
	public String login(Model model) {
		// Title page
		model.addAttribute("pageTitle", "Login");
		return "user/pageLogin";
	}

	/**
	 * Process default URL login failed
	 * @param username
	 * @param password
	 * @return page Login and message status login
	 */
	@PostMapping("/processUrlFail")
	public String processUrlFail(@RequestParam String username, @RequestParam String password) {
		// Check UserName have been exist, if result = true
		if (checkUserExist(username)) {
			// Find UserEntity by userName, Then check status of User
			UserEntity userEntity = userService.findByUserName(username);
			// Check status User, If Status = Unactive 
			if (userEntity.isEnabled() == Constants.UN_ACTIVE)
				// Return page 401 , announcement User is block
				return "redirect:block/401";
			else
				// Return page login and message login failed
				return "redirect:login?error=true";
		}
		return "redirect:login?error=true";
	}

	/**
	 * Check UserName have been existed
	 * @param userName
	 * @return True if UserName existed, else return False
	 */
	public boolean checkUserExist(String userName) {
		return userService.checkExistsByUserName(userName);
	}

	/**
	 * 
	 * @return page 401 , User is blocked
	 */
	@GetMapping("/block/401")
	public String page404(Model model) {
		// Title page
		model.addAttribute("pageTitle", "Block");
		return "error/401";}
}
