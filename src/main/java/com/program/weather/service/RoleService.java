package com.program.weather.service;

import java.util.List;

import com.program.weather.entity.RoleEntity;
/**
 * Define method for Business Logic Layer Role Service
 * @author USER
 *
 */
public interface RoleService {
	
	 List<RoleEntity> findAllRole(); 
}
