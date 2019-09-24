package com.program.weather.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.program.weather.dto.CurrentWeatherDTO;
import com.program.weather.service.impl.CurrentWeatherServiceImpl;
import com.program.weather.utils.CommonUtil;
import com.program.weather.utils.Constants;

@Component
public class WeatherApi {
	
	@Autowired
	CurrentWeatherServiceImpl weatherService;
	
	
	public CurrentWeatherDTO searchWeather(String name) {
		String URL = Constants.WEATHER_URL+CommonUtil.removeAccent(name)+Constants.APPID;
		RestTemplate restTemplate = new RestTemplate();
		CurrentWeatherDTO currentWeather = restTemplate.getForObject(URL, CurrentWeatherDTO.class);
		
		return currentWeather;
	}
	
	public void deleteWeather(Long id) {
		weatherService.deleteWeather(id);
	}
	
	
	
}
