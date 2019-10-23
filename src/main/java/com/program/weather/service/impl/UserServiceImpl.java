package com.program.weather.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.repository.RoleRepository;
import com.program.weather.repository.UserRepository;
import com.program.weather.service.UserService;
import com.program.weather.utils.Constants;

/**
 * Business Logic Layer User Service
 * Process logic and call method from userrepository
 * @author Ngoc Hung
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/** 
	 * Find one user by user name
	 */
	@Override
	public UserEntity findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	/**
	* Add new User and set Role default is USER
	*/
	@Override
	public UserEntity saveUser(UserEntity userEntity) {
		// Call method setinforuser to add property for user
		setInfoForUser(userEntity);
		return userRepository.save(userEntity);
	}

	/**Set property defaut for user such as encodePassword, status
	 * Set role defaut for user is USER
	 * @param userEntity
	 */
	private void setInfoForUser(UserEntity userEntity) {
		// Set password use Encrytedpassword
		userEntity.setEncrytedPassword(passwordEncoder.encode(userEntity.getEncrytedPassword()));
		userEntity.setEnabled(Constants.ACTIVE);
		userEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
		// Set role default is USER
		// RoleEntity userrole = roleRepository.findByRole("ROLE_USER");
		RoleEntity userrole = roleRepository.findByRole(Constants.USER).get();
		userEntity.setRoles(new HashSet<RoleEntity>(Arrays.asList(userrole)));
	}

	/**
	* Delete user by userId
	*/
	@Override
	public void deleteUserById(Long id) {userRepository.deleteById(id);}

	/**
	* Get all list user in DB
	*/
	@Override
	public List<UserEntity> findAllUser(Long userId) {return userRepository.findAllUser(userId);}

	/**
	* Change status active user
	*/
	@Override
	public void updateStatusUser(Long id) {
		UserEntity userEntity = userRepository.findByUserId(id);
		//Check status user and change status
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
	public void updateRoleUser(Long id, String roleName) {
		//Get user and all Role of one
		UserEntity userEntity = userRepository.findByUserId(id);
		RoleEntity roleEntity = roleRepository.findByRole(roleName).get();
		//Set role update
		userEntity.setRoles(new HashSet<RoleEntity>(Arrays.asList(roleEntity)));
		userRepository.save(userEntity);
	}

	/**
	*find user by id
	*/
	@Override
	public UserEntity findByUserId(Long id) {return userRepository.findByUserId(id);}

	/**
	* Check userName of User Exists
	*/
	@Override
	public Boolean checkExistsByUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}

	/**
	* Check email of User Exists
	*/
	@Override
	public Boolean checkExistsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void updatePassword(String password, Long userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		userEntity.setEncrytedPassword(password);
		userRepository.save(userEntity);
	}
}
