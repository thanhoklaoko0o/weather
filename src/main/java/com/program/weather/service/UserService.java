package com.program.weather.service;

import java.util.List;

import com.program.weather.dto.tranfer.UserDTO;
import com.program.weather.entity.UserEntity;
/**
 * Define method for Business Logic Layer User Service
 * @author NgocHung
 *
 */
public interface UserService {

	UserEntity findByUserName(String userName);

	UserEntity findByEmail(String email);

	UserEntity saveUser(UserEntity userEntity);

	UserEntity findByUserId(Long id);

	void updatePassword(String password, Long userId);

	void updateStatusUser(Long id);

	void updateRoleUser(Long id, String roleName);

	void updateProfileUser(UserEntity userEntity, UserDTO userDTO);

	void deleteUserById(Long id);

	List<UserEntity> findAllUser(Long userId);

	Boolean checkExistsByUserName(String userName);

	Boolean checkExistsByEmail(String email);
}
