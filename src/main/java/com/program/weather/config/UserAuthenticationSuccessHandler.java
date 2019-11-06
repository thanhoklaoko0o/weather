package com.program.weather.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
/**
 * Class handle process when Login successfully
 * @author NgocHung
 *
 */
@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		boolean hasUserRole = false;
		boolean hasAdminRole = false;
		// Get role when has User login successfully
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		// Check roleName in list Role of USER
		for (GrantedAuthority grantedAuthority : authorities) {
			// Set hasUserRole = True, If in list has ROLE_USER
			if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
				hasUserRole = true;
				break;
			// Set hasAdminRole = True, If in list has ROLE_ADMIN
			} else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				hasAdminRole = true;
				break;
			}
		}
		//Redirect page with role name is USER
		if (hasUserRole) {
			redirectStrategy.sendRedirect(request, response, "/home-weather");
		//Redirect page with role name is ADMIN
		} else if (hasAdminRole) {
			redirectStrategy.sendRedirect(request, response, "/home-admin");
		} else {
			throw new IllegalStateException();
		}
	}
}
