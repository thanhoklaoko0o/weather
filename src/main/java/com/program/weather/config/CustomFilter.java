package com.program.weather.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class CustomFilter extends GenericFilterBean {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest  req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.getPrincipal() != null) {
			
					if (authentication.getPrincipal() instanceof UserDetails) {
						
						// if login then authentication.getCredentials() null
						if (authentication.getCredentials() == null) {
							// get user name of user logged saved by getPrincipal()
							String username = ((UserDetails) authentication.getPrincipal()).getUsername();
							if (!username.isEmpty()) {
								UserDetails userDetailsQuery = null;
								try {
									userDetailsQuery = userDetailsService.loadUserByUsername(username);
									if (!userDetailsQuery.isEnabled()) {
										new SecurityContextLogoutHandler().logout(req, res, authentication);
									}
								} catch (Exception e) {
								}
								if (userDetailsQuery == null) {
								new SecurityContextLogoutHandler().logout(req, res, authentication);
							}
						
						}
					}
				}
		}
	
		chain.doFilter(req, res);
	}

	public CustomFilter(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public CustomFilter() {
	}
}
