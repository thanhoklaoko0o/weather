package com.program.weather.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.program.weather.entity.RoleEntity;
/**
 *  Data Access Layer Role with DB
 * @author USER
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long>{
	
	 RoleEntity findByRole(String role);
}
