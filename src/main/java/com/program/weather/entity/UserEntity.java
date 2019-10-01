package com.program.weather.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
	
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$", message= "Only have character a-z, A-Z, 0-9 and Length 8 - 32 char")
	@Column(name = "user_name")
    private String userName;
 
	@Email(regexp="[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{1,}[.]{1}[a-zA-Z]{2,}")
    @Column(name = "email")
    private String email;
 
	@NotEmpty
    @Column(name = "encryted_password")
    private String encrytedPassword;
    
	@NotEmpty
    @Column(name = "firstname")
    private String firstName;
    
	@NotEmpty
    @Column(name = "lastname")
    private String lastName;
 
    @Column(name = "enabled")
    private boolean enabled;
    
    @Column(name = "create_date")
    private Timestamp createDate;
    

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles;
	
	
	
	
	
	
	
}
