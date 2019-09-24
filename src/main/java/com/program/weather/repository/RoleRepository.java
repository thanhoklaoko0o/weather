package com.program.weather.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.program.weather.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long>{
	 RoleEntity findByRole(String role);
}
