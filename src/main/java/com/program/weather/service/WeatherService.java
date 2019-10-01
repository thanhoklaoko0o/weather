package com.program.weather.service;

import java.util.List;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;

public interface WeatherService {
	void 					   saveWeather			 (WeatherEntity weatherEntity);
	void            		   deleteWeather   		 (Long id);
	void            		   deleteWeather   		 (WeatherEntity weatherEntity);
	List<WeatherEntity> 	   findAllByUserEntities (UserEntity userEntity);
	List<WeatherEntity>findDateTimeByUserGroupbyDateTimeDest(long id);
	List<WeatherEntity>findDateTimeByUserGroupbyDateTimeAcs (long id);
}
