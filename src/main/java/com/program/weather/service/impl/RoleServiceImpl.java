package com.program.weather.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.weather.entity.RoleEntity;
import com.program.weather.repository.RoleRepository;
import com.program.weather.service.RoleService;

/**
 * Business Logic Layer Role Service
 * 
 * @author NgocHung
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	/**
	 * Get list role entity in DB
	 */
	@Override
	public List<RoleEntity> findAllRole() {return roleRepository.findAll();}
}
