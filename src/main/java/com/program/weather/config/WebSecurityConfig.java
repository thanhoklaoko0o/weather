package com.program.weather.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * Configuration security
 * @author NgocHung
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomFilter customFilter;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private UserAuthenticationSuccessHandler successHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	/**
	 * Provider userDetailsService
	 * provider type encode password for security process
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * Configure security
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.addFilterAt(customFilter, UsernamePasswordAuthenticationFilter.class);
		// Config page user can access
		http.authorizeRequests().antMatchers("/", "/login", "/logout","/forgot-password**","/reset-password**").permitAll();
		// Config page user can access by role is USER or ADMIN
		http.authorizeRequests().antMatchers("/profile-user", "/profile-user/**", "/home-weather", "/home-weather/**").access("hasAnyRole('USER', 'ADMIN')");
		// Config page user can access by role is ADMIN
		http.authorizeRequests().antMatchers("/home-admin", "/home-admin/**").access("hasRole('ADMIN')");
		// Config page user can't access 
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/home-weather/403");
		// Config form login
		http.authorizeRequests().and().formLogin()//
				.loginPage("/login")//
				.loginProcessingUrl("/form-login")
				// Handel class  ro process when login sucessfull
				.successHandler(successHandler)
				// Handel URL  ro process when login failed
				.failureForwardUrl("/processUrlFail")
				// Provide username from login
				.usernameParameter("username")
				// Provide password from login
				.passwordParameter("password");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**");
	}

	/*
	 * @Bean public DaoAuthenticationProvider authenticationProvider() { final
	 * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	 * authProvider.setUserDetailsService(userDetailsService);
	 * authProvider.setPasswordEncoder(encoder()); return authProvider; }
	 */
}
