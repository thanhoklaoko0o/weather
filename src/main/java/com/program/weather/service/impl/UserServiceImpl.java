package com.program.weather.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.repository.RoleRepository;
import com.program.weather.repository.UserRepository;
import com.program.weather.service.UserService;
import com.program.weather.utils.Constants;
import com.program.weather.utils.EncrytedPasswordUtils;
/**
 * Business Logic Layer User Service
 * @author USER
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private WeatherServiceImpl weatherServiceImpl;
	
	/**
	 * Find one User By Username
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
		//set property for info USER
		user.setEncrytedPassword(EncrytedPasswordUtils.encrytePassword(user.getEncrytedPassword()));
		user.setEnabled(true);
		user.setCreateDate(new Timestamp(System.currentTimeMillis()));
		
		//set role default is USER
		RoleEntity userrole = roleRepository.findByRole("USER");
		user.setRoles(new HashSet<RoleEntity>(Arrays.asList(userrole)));
		
		return userRepository.save(user);
	}

	/**
	 * Delete user by userId
	 */
	@Override
	public void delete(Long id) {
		
		UserEntity userEntity= userRepository.findByUserId(id);
		List<WeatherEntity> listWeather = weatherServiceImpl.findAllByUserEntities(userEntity);
		userEntity.getRoles().removeAll(userEntity.getRoles());
		userRepository.delete(userEntity);
		weatherServiceImpl.deleteAllWeatherByUser(listWeather);
		
	}

	/**
	 * Get all list user in DB
	 */
	@Override
	public List<UserEntity> findAll() {
		
		return userRepository.findAll();
	}

	/**
	 * Change status active User
	 */
	@Override
	public void editActiveUser(Long id) {
		
		UserEntity userEntity = userRepository.findByUserId(id);
		
		//check status user and change status
		if(userEntity.isEnabled()) {
			
			userEntity.setEnabled(Constants.UN_ACTIVE);
		}else {
			
			userEntity.setEnabled(Constants.ACTIVE);
		}
		
		userRepository.save(userEntity);
	}
	
	
	/**
	 * Change role User , USER <-> ADMIN <-> USERADMIN
	 */
	@Override
	public void editRoleUser(Long id , String roleName) {
		
		//get user and all Role of one
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
