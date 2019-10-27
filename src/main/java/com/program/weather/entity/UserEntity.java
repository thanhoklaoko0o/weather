package com.program.weather.entity;

import java.sql.Timestamp;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 
 * @author Ngoc Hung
 *
 */
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

	@Column(name = "user_name")
	private String userName;

	@Column(name = "email")
	private String email;

	@Column(name = "encryted_password")
	private String encrytedPassword;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "create_date")
	private Timestamp createDate;

	@Column(name = "modified_date")
	private Timestamp modifiedDate;

	@Column(name = "avatar")
	private String avatar;

	public UserEntity (String userName, String email, String encrytedPassword, String firstName, String lastName, String avatar) {
		this.userName = userName;
		this.email = email;
		this.encrytedPassword = encrytedPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.avatar = avatar;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles;
	
	@OneToMany(mappedBy = "userEntity", cascade=CascadeType.REMOVE)
	@JsonIgnore
	private List<WeatherEntity> listWeather;
}
