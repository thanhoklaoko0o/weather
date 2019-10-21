package com.program.weather.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.program.weather.converter.UserConverter;
import com.program.weather.dto.UserDTO;
import com.program.weather.entity.RoleEntity;
import com.program.weather.service.RoleService;
import com.program.weather.service.UserService;
/**
 * 
 * @author NgocHung
 *
 */
@Controller
@RequestMapping("/home-admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserConverter userConverter;

	/**
	 * Load pageAdmin show all USER when admin access to APP
	 * 
	 * @param model
	 * @param principal
	 * @return view admin
	 */
	@GetMapping
	public String homeAdmin(Model model) {
		// Get list User in DB
		List<UserDTO> lstUser = userService.findAllUser().stream()
								.map(x -> userConverter.convertUserToDTO(x))
								.collect(Collectors.toList());
		// Get list Role in DB
		List<RoleEntity> lstRole = roleService.findAllRole();
		//Tranfer data from list to view admin
		model.addAttribute("lstUser", lstUser);
		model.addAttribute("lstRole", lstRole);
		return "admin/pageAdmin";
	}

	/**
	 * Delete USER bu Id
	 * 
	 * @param id
	 */
	@PostMapping("/delete")
	@ResponseBody
	public void deleteUser(@RequestParam Long id) {userService.deleteUserById(id);}

	/**
	 * Edit enable of user
	 * 
	 * @param id
	 */
	@PostMapping("/editActiveUser")
	@ResponseBody
	public void editActive(@RequestParam Long id) {userService.updateStatusUser(id);}

	/**
	 * Change role of user
	 * 
	 * @param id
	 * @param role
	 */
	@PostMapping("/change-role")
	@ResponseBody
	public void changeRole(@RequestParam Long id, @RequestParam String roleName) {userService.updateRoleUser(id, roleName);}
}
