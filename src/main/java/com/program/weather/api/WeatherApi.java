package com.program.weather.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.program.weather.converter.WeatherConverter;
import com.program.weather.dto.CurrentWeatherDTO;
import com.program.weather.dto.DetailsWeatherDTO;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.service.impl.WeatherServiceImpl;
import com.program.weather.utils.Constants;

@Component
public class WeatherApi {
	
	@Autowired
	WeatherServiceImpl weatherService;
	
	@Autowired
	WeatherConverter weatherConverter;
	
	
	/*
	 * public CurrentWeatherEntity searchWeather(String name) { WeatherApi
	 * weatherApi = new WeatherApi(); return
	 * weatherApi.restJsonData(CommonUtil.removeAccent(name)); }
	 */
	
	public void deleteWeather(Long id) {
		weatherService.deleteWeather(id);
	}
	
	public List<WeatherEntity> findAllByUserEntities(UserEntity userEntity){
		return weatherService.findAllByUserEntities(userEntity);
	}
	
	
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
	
	
	
	
	
}
