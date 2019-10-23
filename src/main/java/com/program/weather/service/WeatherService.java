package com.program.weather.service;

import java.util.List;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.dto.display.CurrentWeatherLocationDTO;
import com.program.weather.dto.tranfer.DetailsWeatherDTO;
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

	List<WeatherEntity> findAllByUserByDateDesc(Long userId);

	List<WeatherEntity> findAllByUserByDateAsc(Long userId);

	WeatherEntity getWeatherByApi(String nameCity);

	DetailsWeatherDTO foreCast(String nameCity);

	CurrentWeatherLocationDTO getWeatherCurWeather(String lat, String lon);
}
