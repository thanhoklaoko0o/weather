package com.program.weather.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.program.weather.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	 @Autowired
	 private CustomUserDetailsService customUserDetailsService;
	 
	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        return bCryptPasswordEncoder;
	    }
	 
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        // Sét đặt dịch vụ để tìm kiếm User trong Database.
	        // Và sét đặt PasswordEncoder.
	        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	 
	    }
	 
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable();
	        http.authorizeRequests().antMatchers("/","/login", "/logout").permitAll();
	        http.authorizeRequests().antMatchers("/home-weather", "/home-weather/**").access("hasAnyRole('USER', 'ADMIN')");
	        http.authorizeRequests().antMatchers("/home-admin", "/home-admin/**").access("hasRole('ADMIN')");
	        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
	 
	        // Cấu hình cho Login Form.
	        http.authorizeRequests().and().formLogin()//
	                // Submit URL của trang login
	                .loginProcessingUrl("/form-login") // Submit URL
	                .loginPage("/login")//
	                .defaultSuccessUrl("/home-weather")//
	                .failureUrl("/login?error=true")//
	                .usernameParameter("username")//
	                .passwordParameter("password")
	                // Cấu hình cho Logout Page.
	                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
	 
	        
	 
	    }
}
