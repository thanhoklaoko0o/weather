package com.program.weather.dto;

import javax.validation.constraints.NotEmpty;

import com.program.weather.validator.PasswordMatches;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@PasswordMatches
public class PasswordResetDTO {

	@NotEmpty
	private String password;

	@NotEmpty
	private String confirmPassword;

	@NotEmpty
	private String token;
}
