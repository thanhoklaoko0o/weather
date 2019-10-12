package com.program.weather.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.program.weather.converter.UserConverter;
import com.program.weather.dto.UserDTO;
import com.program.weather.entity.RoleEntity;
import com.program.weather.service.impl.RoleServiceImpl;
import com.program.weather.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/home-admin")
public class AdminController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private RoleServiceImpl roleServiceImpl;

	@Autowired
	private UserConverter userConverter;

	/**
	 * Load pageAdmin show all USER when admin access to APP
	 * 
	 * @param model
	 * @param principal
	 * @return
	 */
	@GetMapping
	public String homeAdmin(Model model) {

		List<UserDTO>    lstUser = userServiceImpl.findAllUser().stream()
									.map(x -> userConverter.convertUserToDTO(x))
									.collect(Collectors.toList());

		List<RoleEntity> lstRole = roleServiceImpl.findAllRole();

		model.addAttribute("lstUser", lstUser);
		model.addAttribute("lstRole", lstRole);

		return "admin/pageAdmin";
	}

	/**
	 * Delete user
	 * 
	 * @param id
	 */
	@GetMapping("/delete")
	@ResponseBody
	public void deleteUser(@RequestParam Long id) {

		userServiceImpl.deleteUserById(id);
	}

	/**
	 * Edit enable of user
	 * 
	 * @param id
	 */
	@GetMapping("/editActiveUser")
	@ResponseBody
	public void editActive(@RequestParam Long id) {
		
		userServiceImpl.editActiveUser(id);
	}

	/**
	 * Change role of user
	 * 
	 * @param id
	 * @param role
	 */
	@GetMapping("/change-role")
	@ResponseBody
	public void changeRole(@RequestParam Long id, @RequestParam String roleName) {
		userServiceImpl.editRoleUser(id, roleName);
	}
}
