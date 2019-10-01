package com.program.weather.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.converter.WeatherConverter;
import com.program.weather.dto.CurrentWeatherDTO;
import com.program.weather.dto.DetailsWeatherDTO;
import com.program.weather.entity.UserEntity;
import com.program.weather.repository.WeatherRepository;
import com.program.weather.service.WeatherService;
import com.program.weather.utils.Constants;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	WeatherRepository weatherRepository;
	
	@Autowired
	WeatherConverter weatherConverter;

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

	
	  @Override public List<WeatherEntity> findAllByUserEntities(UserEntity
	  userEntity) { return weatherRepository.findAllByUserEntities(userEntity); }
	 
	  public WeatherEntity restJsonData(String name) {
			String URL = Constants.WEATHER_URL+name+Constants.APPID;
			RestTemplate restTemplate = new RestTemplate();
			CurrentWeatherDTO weatherDTO = restTemplate.getForObject(URL, CurrentWeatherDTO.class);
			return  weatherConverter.convertToEntity(weatherDTO);
		}
		
		public DetailsWeatherDTO foreCast(String name) {
			String URL = Constants.FORECAST_URL+name+Constants.APPID;
			RestTemplate restTemplate = new RestTemplate();
			return restTemplate.getForObject(URL, DetailsWeatherDTO.class);
		}

		@Override
		public void deleteWeather(WeatherEntity weatherEntity) {
			 weatherEntity.getUserEntities().removeAll(weatherEntity.getUserEntities());
			  weatherRepository.delete(weatherEntity);
			
		}

		@Override
		public List<WeatherEntity> findDateTimeByUserGroupbyDateTimeDest(long id) {
			
			return weatherRepository.findDateTimeByUserGroupbyDateTimeDest(id);
		}

		@Override
		public List<WeatherEntity> findDateTimeByUserGroupbyDateTimeAcs(long id) {

			return weatherRepository.findDateTimeByUserGroupbyDateTimeAcs(id);
		}

}
