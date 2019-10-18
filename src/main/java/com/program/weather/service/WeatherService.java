package com.program.weather.service;

import java.util.List;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;
/**
 * Define method for Business Logic Layer Weather Service
 * @author Ngoc Hung
 *
 */
public interface WeatherService {
	//Save weather entity in DB
	void saveWeather(WeatherEntity weatherEntity);
	//Delete weather by weather id
	void deleteWeatherById(Long id);
	//Get all list weather by user 
	List<WeatherEntity> findAllByUserEntity(UserEntity userEntity);
	//Get all list weather by user and order by date Desc
	List<WeatherEntity> findAllByUserByDateDesc(UserEntity userEntity);
	//Get all list weather by user and order by date Asc
	List<WeatherEntity> findAllByUserByDateAsc(UserEntity userEntity);
}
