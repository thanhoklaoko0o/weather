package com.program.weather.service;

import java.util.List;

import com.program.weather.entity.UserEntity;
/**
 * Define method for Business Logic Layer User Service
 * @author USER
 *
 */
public interface UserService {

	UserEntity 			findByUserName(String userName);

	UserEntity 			saveUser(UserEntity userEntity);

	UserEntity 			findByUserId(Long id);

	void	 			editActiveUser(Long id);

	void 				editRoleUser(Long id, String role);

	void 				deleteUserById(Long id);

	List<UserEntity> 	findAllUser();

	Boolean 			checkExistsByUserName(String userName);

	Boolean 			checkExistsByEmail(String email);

}
