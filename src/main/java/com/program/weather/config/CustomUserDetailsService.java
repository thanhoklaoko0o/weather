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

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		UserEntity user = userRepository.findByUserName(userName);
		
		if(user == null) {
			
			throw new UsernameNotFoundException("Nguoi dung khong ton tai voi ten : " + userName);
		}
		
			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
			Set<RoleEntity> roles					 = user.getRoles();

			for (RoleEntity role : roles) {
				grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRole()));
			}
			UserDetails userDetails = (UserDetails) new User (user.getUserName(),user.getEncrytedPassword(),user.isEnabled(),
																true,true,true,grantedAuthorities);
			return userDetails;
	}
}
