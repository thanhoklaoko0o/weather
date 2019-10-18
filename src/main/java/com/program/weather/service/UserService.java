package com.program.weather.service;

import java.util.List;

import com.program.weather.entity.UserEntity;
/**
 * Define method for Business Logic Layer User Service
 * @author Ngoc Hung
 *
 */
public interface UserService {
	//Find one userentity by username
	UserEntity findByUserName(String userName);
	//Save one userentity in DB
	UserEntity saveUser(UserEntity userEntity);
	//Find one userrntity by userid
	UserEntity findByUserId(Long id);
	//update status user by userid
	void updateStatusUser(Long id);
	//update role user by userid and rolename
	void updateRoleUser(Long id, String roleName);
	//delete one user by userid
	void deleteUserById(Long id);
	//Get all user in DB
	List<UserEntity> findAllUser();
	//Check username of user exists in DB
	Boolean checkExistsByUserName(String userName);
	//Check email of user exists in DB
	Boolean checkExistsByEmail(String email);
}
