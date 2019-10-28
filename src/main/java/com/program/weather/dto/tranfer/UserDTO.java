package com.program.weather.dto.tranfer;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.program.weather.validator.PasswordMatches;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 
 * @author NgocHung
 *
 */
@Getter
@Setter
@NoArgsConstructor
@PasswordMatches
public class UserDTO {
	
	private Long userId;

	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$", message = "{user.username.msg}")
	private String userName;

	@Email(message = "{user.email.msg}")
	private String email;

	@Size(min = 8, max = 32, message = "{user.password.msg}")
	private String encrytedPassword;

	@Size(min = 8, max = 32, message = "{user.password.msg}")
	private String confirmPassword;

	@NotEmpty(message = "{user.firstname.msg}")
	private String firstName;

	@NotEmpty(message = "{user.lastname.msg}")
	private String lastName;

	private boolean enabled;

	private Set<Long> roles;

	public UserDTO(Long userId, String userName, String email, String encrytedPassword, String firstName, String lastName, boolean enabled) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.encrytedPassword = encrytedPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = enabled;
	}
}
