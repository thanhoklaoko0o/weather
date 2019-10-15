package com.program.weather.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long 	  userId;
    
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$", message= "Only have character a-z, A-Z, 0-9 and Length 8 - 32 char")
    private String 	  userName;
    
    @Email(regexp="[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{1,}[.]{1}[a-zA-Z]{2,}")
    private String    email;
    
    @Size(min=8, max=32, message= "Password must have 8 - 32 characters")
    private String encrytedPassword;
    
    @NotEmpty
    private String 	  firstName;
    
    @NotEmpty
    private String    lastName;
    
    private boolean   enabled;
    
	private Set<Long> roles;

	public UserDTO(Long userId, String userName, String email, String firstName, String lastName, boolean enabled) {
		super();
		this.userId 	= userId;
		this.userName   = userName;
		this.email		= email;
		this.firstName  = firstName;
		this.lastName   = lastName;
		this.enabled    = enabled;
	}
}
