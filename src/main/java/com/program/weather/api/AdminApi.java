package com.program.weather.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.service.impl.RoleServiceImpl;
import com.program.weather.service.impl.UserServiceImpl;

@Component
public class AdminApi {
		
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	RoleServiceImpl roleServiceImpl;
	
	
	public void deleteUser(long id) {
		userService.delete(id);
	}
	
	public List<UserEntity> findAll(){
		return userService.findAll();
	}
	
	public UserEntity getUser(String name) {
		return userService.findByUserName(name);
	}
	
	public void editActiveUser (Long id) {
		userService.editActiveUser(id);
	}
	
	 
    public UserEntity findUserById(Long id) {
    	return userService.findByUserId(id);
    }
    
    public void editRoleUser(Long id , String role) {
    	 userService.editRoleUser(id, role);
    }
    
    public List<RoleEntity> findAllRole() {
		return roleServiceImpl.findAll();
	}
	
}
