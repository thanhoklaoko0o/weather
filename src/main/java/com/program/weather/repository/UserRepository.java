package com.program.weather.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.program.weather.entity.UserEntity;

/**
 *  Data Access Layer USER with DB
 * @author USER
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByUserName   (String userName);
    UserEntity findByUserId		(Long 	id);
    Boolean    existsByUserName (String userName);
    Boolean    existsByEmail	(String email);
    
}
