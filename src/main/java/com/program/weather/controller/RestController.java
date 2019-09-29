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
import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.repository.WeatherRepository;
import com.program.weather.repository.UserRepository;
import com.program.weather.utils.CommonUtil;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
	
	@Autowired
	private WeatherApi weatherApi;
	
	@Autowired
	private UserApi userApi;
	
	@Autowired
	private AdminApi adminApi;
	
	
	
	/*
	 * @GetMapping("/search-city/{name}") public CurrentWeatherDTO
	 * home(@PathVariable String name) { CurrentWeatherDTO currentWeather =
	 * weatherApi.searchWeather(name); return currentWeather; }
	 */
	
	
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
	
	@Autowired
	WeatherRepository currentWeatherRepository;
	
	@GetMapping("/findAllWeatherByUserId")
	public List<WeatherEntity> getallbyuserid(){
		UserEntity entity = userRepository.findByUserName("usernamead");
		return currentWeatherRepository.findAllByUserEntities(entity);
	}
	
	@GetMapping("/checkExistsCity/{name}")
	public Boolean checkExistsCity(@PathVariable String name) {
		return currentWeatherRepository.existsByNameCity(name);
	}
	
	@GetMapping("/findAllByDate/{ts}")
	public List<WeatherEntity> getallbyuserid(@PathVariable Timestamp ts){
		return currentWeatherRepository.findAllByDate(ts);
	}
	
	@GetMapping("/countWeather/{name}")
	public Long coutWeatherByName(@PathVariable String name) {
		return currentWeatherRepository.countAllByNameCity(name);
	}
	
	@GetMapping("/deleteWeather/{id}")
	public void deleteWeather(@PathVariable long id) {
		weatherApi.deleteWeather(id);
	}
	
	@GetMapping("/updateWeather/{name}")
	public void updateWeather(@PathVariable String name) {
		
		UserEntity userEntity = userRepository.findByUserName("usernamead");
		List<WeatherEntity> listByUser=currentWeatherRepository.findAllByUserEntities(userEntity);
//		List<CurrentWeatherEntity> listByNameCity = new ArrayList<CurrentWeatherEntity>();
		
		for(WeatherEntity entity :listByUser) {
			if(entity.getNameCity().equals(name)) {
				String d= CommonUtil.formatToString(entity.getDate());
				if(d.equals(CommonUtil.curTimeToString())) {
					entity.setCreateBy("Ngoc Hung");
					entity.setPressure("abcdefgh");
					currentWeatherRepository.save(entity);
				}
			}
				
		}
		
		
		
		
		
	}
	
	
	
}
