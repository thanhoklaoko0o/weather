package com.program.weather.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	@Modifying
	@Query("update User u set u.password = :password where u.id = :id")
	void updatePassword(@Param("password") String password, @Param("id") Long id);
}
