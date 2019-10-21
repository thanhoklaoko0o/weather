package com.program.weather.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.program.weather.service.impl.UserServiceImpl;

/**
 * 
 * @author Ngoc Hung
 *
 */
@Controller
public class AccountController {

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private UserServiceImpl userServiceImpl;

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
	 * @return view login if register sucessfully, else view register
	 */
	@PostMapping("/registerAccount")
	public String processRegister(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result) {
		//Check error when username exist
		if (userServiceImpl.checkExistsByUserName(userDTO.getUserName())) {
			return "redirect:registerAccount?msgUserName=Register_Failed";
		}
		//Check error when email exist
		if (userServiceImpl.checkExistsByEmail(userDTO.getEmail())) {
			return "redirect:registerAccount?msgEmail=Register_Failed";
		}
		//Check error when validate bean
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
	 * @return true if exists, else false
	 */
	@PostMapping("/checkUserName")
	@ResponseBody
	public String checkExistsByUserName(@RequestParam String userName) {
		Boolean result = userServiceImpl.checkExistsByUserName(userName);
		return "" + result;
	}

	/**
	 * Check exists email in DB
	 * 
	 * @param email
	 * @return true if exists, else false
	 */
	@PostMapping("/checkEmail")
	@ResponseBody
	public String checkExistsByEmail(@RequestParam String email) {
		Boolean result = userServiceImpl.checkExistsByEmail(email);
		return "" + result;
	}
}
