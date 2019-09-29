package com.program.weather.service;

import java.util.List;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;

public interface WeatherService {
	void 					   saveWeather			 (WeatherEntity weatherEntity);
	void            		   deleteWeather   		 (Long id);
	List<WeatherEntity> findAllByUserEntities (UserEntity userEntity);
}
