package com.program.weather.dto;

import javax.validation.constraints.NotEmpty;

import com.program.weather.validator.FieldMatch;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@FieldMatch(first = "password", second = "confirmPassword", message = "{user.password.confirm.msg}")
public class PasswordResetDTO {

	@NotEmpty
	private String password;

	@NotEmpty
	private String confirmPassword;

	@NotEmpty
	private String token;
}
