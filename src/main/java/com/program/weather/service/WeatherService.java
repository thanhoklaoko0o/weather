package com.program.weather.service;

import java.util.List;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;
/**
 * Define method for Business Logic Layer Weather Service
 * @author USER
 *
 */
public interface WeatherService {
	
	void 					   saveWeather			 		(WeatherEntity weatherEntity);
	void            		   deleteWeatherById   			(Long id);
	void					   deleteAllWeatherByUser		(List<WeatherEntity> listWeather);
	List<WeatherEntity> 	   findAllByUserEntities 		(UserEntity userEntity);
	List<WeatherEntity>findDateTimeByUserGroupbyDateTimeDest(long id);
	List<WeatherEntity>findDateTimeByUserGroupbyDateTimeAcs (long id);
}
