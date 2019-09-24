package com.program.weather.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.weather.entity.CurrentWeatherEntity;
import com.program.weather.repository.CurrentWeatherRepository;
import com.program.weather.service.CurrentWeatherService;

@Service
public class CurrentWeatherServiceImpl implements CurrentWeatherService {
	
	@Autowired
	CurrentWeatherRepository weatherRepository;

	@Override
	public void saveWeather(CurrentWeatherEntity weatherEntity) {
		
		weatherRepository.save(weatherEntity);
		
	}

	@Override
	public void updateWeather(Long id, String nameCity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteWeather(Long id) {
		CurrentWeatherEntity weatherEntity = weatherRepository.findByWeatherId(id);
		weatherRepository.delete(weatherEntity);
		
	}

	
	
	
	
}
