package com.program.weather.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class PasswordForgotDTO {

	@Email
	@NotEmpty
	private String email;
}
