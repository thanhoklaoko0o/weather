package com.program.weather.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.program.weather.api.AdminApi;
import com.program.weather.api.UserApi;
import com.program.weather.api.WeatherApi;
import com.program.weather.converter.CurrentWeatherConverter;
import com.program.weather.dto.CurrentWeatherDTO;
import com.program.weather.entity.CurrentWeatherEntity;
import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.repository.CurrentWeatherRepository;
import com.program.weather.repository.UserRepository;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
	
	@Autowired
	private WeatherApi weatherApi;
	
	@Autowired
	private UserApi userApi;
	
	@Autowired
	private AdminApi adminApi;
	
	@Autowired
	private CurrentWeatherConverter converterEntity;
	
	
	@GetMapping("/search-city/{name}")
	public CurrentWeatherDTO home(@PathVariable String name) {
	   CurrentWeatherDTO currentWeather = weatherApi.searchWeather(name);
	return currentWeather;
	}
	
	
	
	@PostMapping("/save-user")
	public UserEntity saveUser(@RequestBody UserEntity userEntity) {
		return userApi.saveUser(userEntity);
	}
	
	@GetMapping("/delete/{id}")
	public void detele(@PathVariable Long id) {
		adminApi.deleteUser(id);
	}
	
	@GetMapping("/getAll")
	public List<UserEntity> getAll(){
		return adminApi.findAll();
	}
	
	@Autowired 
	UserRepository userRepository;
	
	@GetMapping("/find-user/{name}")
	public UserEntity user(@PathVariable String name) {
	  
	return userRepository.findByUserName(name);
	}
	
	@GetMapping("/edit-user/{id}")
	public void edit(@PathVariable Long id) {
		adminApi.editActiveUser(id);	
	}
	
	@GetMapping("/find/{id}")
	 public UserEntity findUserById(@PathVariable Long id) {
		 return adminApi.findUserById(id);
	 }
	
	@GetMapping("/change-role/{id}/{role}")
	 public void changeRole(@PathVariable Long id,@PathVariable String role) {
		  adminApi.editRoleUser(id, role);
	 }
	
	@GetMapping("/getAllRole")
	public List<RoleEntity> findAllRole() {
		return adminApi.findAllRole();
	}
	
	//--------API Weather-----------//
	
	@GetMapping("/delete-weather/{id}")
	public void deleteWeather(@PathVariable Long id) {
		weatherApi.deleteWeather(id);
	}
	
	
	//update weather
	@GetMapping("/update-weather")
	public void updateWeather(@PathVariable String name) {
		 CurrentWeatherDTO currentWeather = weatherApi.searchWeather(name);
		 CurrentWeatherEntity weatherEntity = converterEntity.convertToEntity(currentWeather);
		 
	}
	
	//findAll by idUser
	
	@Autowired
	CurrentWeatherRepository a;
	
	@GetMapping("/findAllByUserId/{id}")
	public List<CurrentWeatherEntity> getallbyuserid(@PathVariable Long id){
		return a.findAllByCreateBy(id);
	}
	
	@GetMapping("/checkExistsCity/{name}")
	public Boolean checkExistsCity(@PathVariable String name) {
		return a.existsByNameCity(name);
	}
	
	@GetMapping("/findAllByDate/{ts}")
	public List<CurrentWeatherEntity> getallbyuserid(@PathVariable Timestamp ts){
		return a.findAllByDate(ts);
	}
	
	
	
}
