package com.program.weather.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.program.weather.dto.UserDTO;
import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.service.impl.RoleServiceImpl;
import com.program.weather.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/home-admin")
public class AdminController {

	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private RoleServiceImpl roleServiceImpl;
	
	/**
	 * Load pageAdmin show all USER when admin access to APP
	 * @param model
	 * @param principal
	 * @return
	 */
	@GetMapping
	public String homeAdmin(Model model) {
		
		List<UserDTO>	 lstUser = userServiceImpl.findAll().stream().map(AdminController::castUserToDTO).collect(Collectors.toList());
		List<RoleEntity> lstRole = roleServiceImpl.findAll();

		model.addAttribute("lstUser", lstUser);
		model.addAttribute("lstRole", lstRole);
		
		return "admin/pageAdmin";
	}
	
	private static UserDTO castUserToDTO(UserEntity userEntity) {
		UserDTO user =  new UserDTO(userEntity.getUserId(), userEntity.getUserName(), userEntity.getEmail()
								  , userEntity.getFirstName(), userEntity.getLastName(), userEntity.isEnabled());
		
		Set<Long> roles = new HashSet<Long>();
		
		userEntity.getRoles().stream().forEach(i->{
			roles.add(i.getRoleId());
		});
		
		user.setRoles(roles);
		
		return user;
	}

	/**
	 * Delete user 
	 * @param id
	 */
	@GetMapping("/delete")
	@ResponseBody
	public void deleteUser(@RequestParam Long id) {
		userServiceImpl.delete(id);
	}

	/**
	 * Edit enable of user
	 * @param id
	 */
	@GetMapping("/editActiveUser")
	@ResponseBody
	public void editActive(@RequestParam Long id) {
		userServiceImpl.editActiveUser(id);
	}

	/**
	 * Change role of user
	 * @param id
	 * @param role
	 */
	@GetMapping("/change-role")
	@ResponseBody
	public void changeRole(@RequestParam Long id, @RequestParam String roleName) {
		userServiceImpl.editRoleUser(id, roleName);
	}
}
