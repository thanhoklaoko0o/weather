package com.program.weather.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.repository.RoleRepository;
import com.program.weather.repository.UserRepository;
import com.program.weather.service.UserService;
import com.program.weather.utils.Constants;
import com.program.weather.utils.EncrytedPasswordUtils;

/**
 * Business Logic Layer User Service
 * 
 * @author Ngoc Hung
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	/**
	 * Find one User By User Name
	 */
	@Override
	public UserEntity findByUserName(String userName) {

		return userRepository.findByUserName(userName);
	}

	/**
	 * Add new User and set Role default is USER
	 */
	@Override
	public UserEntity saveUser(UserEntity user) {
		
		if(roleRepository.findByRole("USER")==null) {
			
			RoleEntity roleEntity = new RoleEntity();
			RoleEntity roleEntitya = new RoleEntity();
			roleEntity.setRole("ADMIN");
			roleEntitya.setRole("USER");
			roleRepository.save(roleEntity);
			roleRepository.save(roleEntitya);
			/*
			 * UserEntity useradmin= new UserEntity();
			 * useradmin.setUserName("AdminWeather");
			 * useradmin.setEmail("NgocHung@gmail.com");
			 * useradmin.setEncrytedPassword(EncrytedPasswordUtils.encrytePassword(
			 * "ngochung")); useradmin.setFirstName("Ngoc"); useradmin.setLastName("Hung");
			 * userRepository.save(useradmin);
			 */
			
		}
		
		// set property for info USER
		user.setEncrytedPassword(EncrytedPasswordUtils.encrytePassword(user.getEncrytedPassword()));
		user.setEnabled(true);
		user.setCreateDate(new Timestamp(System.currentTimeMillis()));

		// set role default is USER
		RoleEntity userrole = roleRepository.findByRole("USER");
		user.setRoles(new HashSet<RoleEntity>(Arrays.asList(userrole)));

		return userRepository.save(user);
	}

	/**
	 * Delete user by userId
	 */
	@Override
	public void deleteUserById(Long id) {

		userRepository.deleteById(id);
	}

	/**
	 * Get all list user in DB
	 */
	@Override
	public List<UserEntity> findAllUser() {

		return userRepository.findAll();
	}

	/**
	 * Change status active User
	 */
	@Override
	public void editActiveUser(Long id) {
		
		UserEntity userEntity = userRepository.findByUserId(id);

		// check status user and change status
		if (userEntity.isEnabled()) {

			userEntity.setEnabled(Constants.UN_ACTIVE);
		} else {

			userEntity.setEnabled(Constants.ACTIVE);
		}
		userRepository.save(userEntity);
	}

	/**
	 * Change role User , USER <-> ADMIN <-> USERADMIN
	 */
	@Override
	public void editRoleUser(Long id, String roleName) {

		// get user and all Role of one
		UserEntity userEntity = userRepository.findByUserId(id);
		RoleEntity roleEntity = roleRepository.findByRole(roleName);

		userEntity.setRoles(new HashSet<RoleEntity>(Arrays.asList(roleEntity)));
		userRepository.save(userEntity);
	}

	/**
	 * find User By id
	 */
	@Override
	public UserEntity findByUserId(Long id) {

		return userRepository.findByUserId(id);
	}

	/**
	 * check userName of User Exists
	 */
	@Override
	public Boolean checkExistsByUserName(String userName) {

		return userRepository.existsByUserName(userName);
	}

	/**
	 * check email of User Exists
	 */
	@Override
	public Boolean checkExistsByEmail(String email) {

		return userRepository.existsByEmail(email);
	}

}
