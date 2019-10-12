/*
 *(C) Copyright 2019
 *@author USER
 *@date   Oct 11, 2019	
 *@version 1.0
 *
 */

package com.program.weather.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.program.weather.dto.UserDTO;
import com.program.weather.entity.UserEntity;
/**
 * Convert info USER , DTO <-> ENTITY
 * @author USER
 *
 */
@Component
public class UserConverter {

	/**
	 * Converter Entity -> DTO
	 * @param userEntity
	 * @return User DTO
	 */
	public UserDTO convertUserToDTO(UserEntity userEntity) {

		UserDTO user = new UserDTO(	 userEntity.getUserId(), userEntity.getUserName(), 
									 userEntity.getEmail(), userEntity.getFirstName(),
									 userEntity.getLastName(), userEntity.isEnabled());

		Set<Long> roles = new HashSet<Long>();
		userEntity.getRoles().stream().forEach(i -> {
			roles.add(i.getRoleId());
		});

		user.setRoles(roles);

		return user;
	}
}
