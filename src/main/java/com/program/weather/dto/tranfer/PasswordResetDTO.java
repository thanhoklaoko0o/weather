package com.program.weather.dto.tranfer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.program.weather.validator.FieldMatch;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@FieldMatch(first = "password", second = "confirmPassword", message = "{user.password.confirm.msg}")
public class PasswordResetDTO {

	@Size(min = 8, max = 32, message = "{user.password.msg}")
	private String password;

	private String confirmPassword;

	@NotEmpty
	private String token;
}
