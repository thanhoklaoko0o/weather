package com.program.weather.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.program.weather.entity.UserEntity;
/**
 *  Data Access Layer USER with DB
 * @author Ngoc Hung
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUserName (String userName);

	UserEntity findByEmail (String email);

	UserEntity findByUserId (Long id);

	Boolean existsByUserName (String userName);

	Boolean existsByEmail (String email);

	@Query(value = "SELECT * FROM user WHERE user_id != ?1", nativeQuery = true)
	List<UserEntity> findAllUser(Long userId);
}
