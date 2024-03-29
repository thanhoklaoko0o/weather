package com.program.weather.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.program.weather.entity.RoleEntity;
/**
 *  Data Access Layer Role with DB
 * @author NgocHung
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long>{

	Optional<RoleEntity> findByRole(String roleName);
}
