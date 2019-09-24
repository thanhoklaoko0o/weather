package com.program.weather.service;

import java.util.List;

import com.program.weather.entity.CurrentWeatherEntity;

public interface CurrentWeatherService {
	void saveWeather(CurrentWeatherEntity weatherEntity);
	void             updateWeather   (Long id,String nameCity);
	void             deleteWeather   (Long id);
	
}
