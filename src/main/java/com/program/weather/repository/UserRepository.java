package com.program.weather.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.program.weather.entity.UserEntity;
/**
 *  Data Access Layer USER with DB
 * @author Ngoc Hung
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	//Find one userentity by username 
	UserEntity findByUserName (String userName);
	//Find one userEntity by userId
	UserEntity findByUserId (Long id);
	//Check username of user in DB had been exists 
	Boolean existsByUserName (String userName);
	//Check email of user in DB had been exists 
	Boolean existsByEmail (String email);
}
