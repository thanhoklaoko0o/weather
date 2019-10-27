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

import com.program.weather.dto.tranfer.UserDTO;
import com.program.weather.entity.UserEntity;
/**
 * 
 * @author Ngoc Hung
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
		UserDTO user = new UserDTO(userEntity.getUserId(), userEntity.getUserName(), 
							userEntity.getEmail(),userEntity.getEncrytedPassword(), userEntity.getFirstName(),
							userEntity.getLastName(), userEntity.isEnabled(),userEntity.getAvatar());
		Set<Long> roles = new HashSet<Long>();
		userEntity.getRoles().stream().forEach(i -> {
			roles.add(i.getRoleId());
		});
		user.setRoles(roles);
		return user;
	}

	/**
	 * Convert DTO -> Entity
	 * @param userDTO
	 * @return
	 */
	public UserEntity convertUserEntity(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity(userDTO.getUserName(), userDTO.getEmail(), 
												userDTO.getEncrytedPassword(), userDTO.getFirstName(),
												userDTO.getLastName(),userDTO.getAvatar().getOriginalFilename());
		return userEntity;
	}
}
