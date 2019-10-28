package com.program.weather.config;
import java.sql.Timestamp;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.repository.RoleRepository;
import com.program.weather.repository.UserRepository;
import com.program.weather.utils.Constants;
/**
 * Create Data Role and Account Admin
 * @author Ngoc Hung
 *
 */
@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired 
	private PasswordEncoder passwordEncoder;

	@Value("${account.admin.user}")
	private String userAdmin;

	@Value("${account.admin.password}")
	private String passwordAdmin;

	@Value("${my.email}")
	private String emaildAdmin;

	@Value("${admin.firstname}")
	private String firstName;

	@Value("${admin.lastname}")
	private String lastName;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// Check ROLE_ADMIN have been existed
		if (!roleRepository.findByRole(Constants.ADMIN).isPresent()) {
			// If result = false, Create ROLE_ADMIN
			roleRepository.save(new RoleEntity(Constants.ADMIN));
		}
		// Check ROLE_USER have been existed
		if (!roleRepository.findByRole(Constants.USER).isPresent()) {
			// If result = false, Create ROLE_USER
			roleRepository.save(new RoleEntity(Constants.USER));
		}

		// Set account admin
		// Check userAdmin have been existed
		if(!userRepository.existsByUserName(userAdmin)) {
			// Create one UserEntity Admin
			UserEntity accountAdmin = new UserEntity();
			// Set property for User has ROLE_ADMIN
			accountAdmin.setUserName(userAdmin);
			accountAdmin.setEmail(emaildAdmin);
			accountAdmin.setEncrytedPassword(passwordEncoder.encode(passwordAdmin));
			accountAdmin.setFirstName(firstName);
			accountAdmin.setLastName(lastName);
			accountAdmin.setEnabled(Constants.ACTIVE);
			accountAdmin.setCreateDate(new Timestamp(System.currentTimeMillis()));
			HashSet<RoleEntity> roles = new HashSet<>();
			roles.add(roleRepository.findByRole(Constants.ADMIN).get());
			accountAdmin.setRoles(roles);
			//Add USER Admin in DB
			userRepository.save(accountAdmin);
		}
	}
}
