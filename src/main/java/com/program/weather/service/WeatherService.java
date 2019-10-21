package com.program.weather.service;

import java.util.List;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.dto.DetailsWeatherDTO;
import com.program.weather.entity.CurrentWeatherEntity;
import com.program.weather.entity.UserEntity;
/**
 * Define method for Business Logic Layer Weather Service
 * @author Ngoc Hung
 *
 */
public interface WeatherService {

	void saveWeather(WeatherEntity weatherEntity);

	void deleteWeatherById(Long id);

	List<WeatherEntity> findAllByUserEntity(UserEntity userEntity);

	List<WeatherEntity> findAllByUserByDateDesc(UserEntity userEntity);

	List<WeatherEntity> findAllByUserByDateAsc(UserEntity userEntity);

	WeatherEntity getWeatherByApi(String nameCity);

	DetailsWeatherDTO foreCast(String nameCity);

	CurrentWeatherEntity getWeatherCurWeather(String lat, String lon);
}
