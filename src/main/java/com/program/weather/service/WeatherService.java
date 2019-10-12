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

	void 				saveWeather(WeatherEntity weatherEntity);

	void 				deleteWeatherById(Long id);

	List<WeatherEntity> findAllByUserEntity(UserEntity userEntity);

	List<WeatherEntity> findAllByUserByDateDesc(UserEntity userEntity);

	List<WeatherEntity> findAllByUserByDateAsc(UserEntity userEntity);
}
