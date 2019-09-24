package com.program.weather.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.program.weather.entity.UserEntity;
import com.program.weather.service.impl.UserServiceImpl;

@Component
public class UserApi {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	public UserEntity saveUser (UserEntity user) {
		 return userServiceImpl.saveUser(user);
	}
}
