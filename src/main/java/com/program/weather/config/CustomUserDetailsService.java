package com.program.weather.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.repository.UserRepository;
/**
 * Implements UserDetailsService  method loadUserByUsername
 * @author Ngoc Hung
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Get USER from userName, Convert User to UserDetails
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// Load USER by userName
		UserEntity user = userRepository.findByUserName(userName);
		// Check USER exist
		if(user == null) {
			throw new UsernameNotFoundException("Nguoi dung khong ton tai voi ten : " + userName);
		}
			// Get role of USER
			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
			Set<RoleEntity> roles					 = user.getRoles();
			// Set role for UserDetails
			for (RoleEntity role : roles) {
				grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
			}
			//Convert USER to UserDetails
			UserDetails userDetails = (UserDetails) new User (user.getUserName(),user.getEncrytedPassword(),user.isEnabled(),
																true,true,true,grantedAuthorities);
			return userDetails;
	}
}
