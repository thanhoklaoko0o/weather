package com.program.weather.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.repository.WeatherRepository;
import com.program.weather.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	WeatherRepository weatherRepository;

	@Override
	public void saveWeather(WeatherEntity weatherEntity) {
		weatherRepository.save(weatherEntity);
	}

	@Override
	public void deleteWeather(Long id) {
		WeatherEntity weatherEntity = weatherRepository.findByWeatherId(id);
		weatherEntity.getUserEntities().removeAll(weatherEntity.getUserEntities());
		weatherRepository.delete(weatherEntity);
	}

	@Override
	public List<WeatherEntity> findAllByUserEntities(UserEntity userEntity) {
		return weatherRepository.findAllByUserEntities(userEntity);
	}

	

}
