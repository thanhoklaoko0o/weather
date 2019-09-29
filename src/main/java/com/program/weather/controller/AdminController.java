package com.program.weather.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.program.weather.api.AdminApi;
import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;

@Controller
@RequestMapping("/home-admin")
public class AdminController {

	@Autowired
	private AdminApi adminApi;
	
	/**
	 * Load pageAdmin when admin access to APP
	 * @param model
	 * @param principal
	 * @return
	 */
	@GetMapping
	public String homeAdmin(Model model, Principal principal) {
		List<UserEntity> dsUser = adminApi.findAll();
		List<RoleEntity> dsRole = adminApi.findAllRole();

		model.addAttribute("dsUser", dsUser);
		model.addAttribute("dsRole", dsRole);
		return "pageAdmin";
	}

	/**
	 * Delete user 
	 * @param id
	 */
	@GetMapping("/delete")
	@ResponseBody
	public void deleteUser(@RequestParam Long id) {
		adminApi.deleteUser(id);
	}

	/**
	 * Edit enable of user
	 * @param id
	 */
	@GetMapping("/editActiveUser")
	@ResponseBody
	public void editActive(@RequestParam Long id) {
		adminApi.editActiveUser(id);
	}

	/**
	 * Change role of user
	 * @param id
	 * @param role
	 */
	@GetMapping("/change-role")
	@ResponseBody
	public void changeRole(@RequestParam Long id, @RequestParam String role) {
		adminApi.editRoleUser(id, role);
	}
}
