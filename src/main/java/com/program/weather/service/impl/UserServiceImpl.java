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

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public UserEntity findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public UserEntity saveUser(UserEntity user) {
		user.setEncrytedPassword(EncrytedPasswordUtils.encrytePassword(user.getEncrytedPassword()));
		user.setEnabled(true);
		user.setCreateDate(new Timestamp(System.currentTimeMillis()));
		RoleEntity userrole = roleRepository.findByRole("USER");
		user.setRoles(new HashSet<RoleEntity>(Arrays.asList(userrole)));
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		UserEntity userEntity= userRepository.findByUserId(id);
		userEntity.getRoles().removeAll(userEntity.getRoles());
		
		userRepository.delete(userEntity);
	}

	@Override
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void editActiveUser(Long id) {
		UserEntity userEntity = userRepository.findByUserId(id);
		if(userEntity.isEnabled()) {
			userEntity.setEnabled(Constants.UN_ACTIVE);
		}else {
			userEntity.setEnabled(Constants.ACTIVE);
		}
		userRepository.save(userEntity);
	}
	
	@Override
	public void editRoleUser(Long id , String role) {
		UserEntity userEntity = userRepository.findByUserId(id);
		RoleEntity roleEntity = roleRepository.findByRole(role);
		userEntity.setRoles(new HashSet<RoleEntity>(Arrays.asList(roleEntity)));
		userRepository.save(userEntity);
	}

	

	@Override
	public UserEntity findByUserId(Long id) {
		
		return userRepository.findByUserId(id);
	}


	

	
}
