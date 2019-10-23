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
 * 
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

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.addFilterAt(customFilter, UsernamePasswordAuthenticationFilter.class);
		http.authorizeRequests().antMatchers("/", "/login", "/logout","/forgot-password**","/reset-password**").permitAll();
		http.authorizeRequests().antMatchers("/home-weather", "/home-weather/**").access("hasAnyRole('USER', 'ADMIN')");
		http.authorizeRequests().antMatchers("/home-admin", "/home-admin/**").access("hasRole('ADMIN')");
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/home-weather/403");

		http.authorizeRequests().and().formLogin()//
				//.loginPage("/login")//
				.loginProcessingUrl("/form-login")
				.successHandler(successHandler)
				.failureForwardUrl("/processUrlFail")
				.usernameParameter("username")//
				.passwordParameter("password")
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**");
	}
	
	/*
	 * @Bean public DaoAuthenticationProvider authenticationProvider() { final
	 * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	 * authProvider.setUserDetailsService(userDetailsService);
	 * authProvider.setPasswordEncoder(encoder()); return authProvider; }
	 */
}
