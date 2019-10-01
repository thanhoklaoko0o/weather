package com.program.weather.service;

import java.util.List;

import com.program.weather.entity.UserEntity;

public interface UserService {
	
	UserEntity       findByUserName 		(String userName);
	UserEntity       saveUser       		(UserEntity userEntity);
	UserEntity       findByUserId   		(Long id);
	void             editActiveUser 		(Long id);
	void             editRoleUser   		(Long id, String role);
	void             delete        		    (Long id);
	List<UserEntity> findAll				();
	Boolean			 checkExistsByUserName  (String userName);
	Boolean			 checkExistsByEmail 	(String email);
	
}
