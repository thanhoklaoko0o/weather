package com.program.weather.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.weather.entity.RoleEntity;
import com.program.weather.repository.RoleRepository;
import com.program.weather.service.RoleService;
/**
 * Business Logic Layer Role Service
 * @author USER
 *
 */
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	/**
	 * Get all role in DB
	 */
	@Override
	public List<RoleEntity> findAll() {
		
		return roleRepository.findAll();
	}

}
