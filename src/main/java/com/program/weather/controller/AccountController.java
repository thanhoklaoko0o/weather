package com.program.weather.controller;

import java.security.Principal;

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
import com.program.weather.dto.tranfer.UserDTO;
import com.program.weather.entity.UserEntity;
import com.program.weather.service.UserService;

/**
 * 
 * @author NgocHung
 *
 */
@Controller
public class AccountController {

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private UserService userService;

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
		// Title page
		model.addAttribute("pageTitle", "Register");
		return "user/register";
	}

	/**
	 * process info User when User register
	 * 
	 * @param userEntity
	 * @param result
	 * @return view login if register successfully, else view register
	 */
	@PostMapping("/registerAccount")
	public String processRegister(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result) {
		// Check error when user name exist
		if (userService.checkExistsByUserName(userDTO.getUserName())) {
			return "redirect:registerAccount?msgUserName=Register_Failed";
		}
		// Check error when email exist
		if (userService.checkExistsByEmail(userDTO.getEmail())) {
			return "redirect:registerAccount?msgEmail=Register_Failed";
		}
		// Check error when validate bean
		if (result.hasErrors()) {
			return "user/register";
		}
		userService.saveUser(userConverter.convertUserEntity(userDTO));
		return "redirect:login?message=Register_Successful";
	}

	/**
	 * Get Info UserEntity
	 * @param model
	 * @param principal
	 * @return page profile
	 */
	@GetMapping("/profile-user")
	public String getProfileUser(Model model, Principal principal) {
		// Get USER when User login successful
		UserDTO userDTO = userConverter.convertUserToDTO(userService.findByUserName(principal.getName()));
		model.addAttribute("userDTO", userDTO);
		// Title page
		model.addAttribute("pageTitle", "Profile");
		return "user/profile";
	}

	/**
	 * Update information User
	 * @param userDTO
	 * @param result
	 * @param principal
	 * @return page profile if successful
	 */
	@PostMapping("/update-profile")
	public String processUpdateUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result, Principal principal) {
		// Check error when validate bean
		if (result.hasErrors()) {
			return "user/profile";
		}
		
		UserEntity userEntity = userService.findByUserName(principal.getName());
		//update info USER
		userService.updateProfileUser(userEntity, userDTO);
		return "redirect:profile-user?message=Update_Successful";
	}

	/**
	 * Check exists user name in DB
	 * 
	 * @param userName
	 * @return true if exists, else false
	 */
	@PostMapping("/checkUserName")
	@ResponseBody
	public String checkExistsByUserName(@RequestParam String userName) {
		Boolean result = userService.checkExistsByUserName(userName);
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
		Boolean result = userService.checkExistsByEmail(email);
		return "" + result;
	}
}
