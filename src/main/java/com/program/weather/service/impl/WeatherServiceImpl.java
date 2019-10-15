package com.program.weather.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.program.weather.converter.WeatherConverter;
import com.program.weather.dto.CurrentWeatherDTO;
import com.program.weather.dto.DetailsWeatherDTO;
import com.program.weather.entity.CurrentWeatherEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.repository.WeatherRepository;
import com.program.weather.service.WeatherService;

/**
 * Business Logic Layer Weather Service
 * 
 * @author USER
 *
 */
@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	WeatherRepository weatherRepository;

	@Autowired
	WeatherConverter weatherConverter;
	
	@Value("${weather.api.key}")
	private String appID;
	
	@Value("${weather.url}")
	private String weatherURL;
	
	@Value("${weather.url.version}")
	private String weatherVersion;
	
	@Value("${weather.url.current}")
	private String weatherCurrent;
	
	@Value("${weather.url.forecast}")
	private String weatherForecast;
	
	/**
	 * Add New Weather In DB
	 */
	@Override
	public void saveWeather(WeatherEntity weatherEntity) {

		weatherRepository.save(weatherEntity);
	}

	/**
	 * Delete Weather By IdWeather
	 */
	@Override
	public void deleteWeatherById(Long id) {

		weatherRepository.deleteById(id);
	}

	/**
	 * Get All List Weather By USER
	 */
	@Override
	public List<WeatherEntity> findAllByUserEntity(UserEntity userEntity) {

		return weatherRepository.findAllByUserEntity(userEntity);
	}

	/**
	 *  //Get weather by User by group by name city by date DESC
	 */
	@Override
	public List<WeatherEntity> findAllByUserByDateDesc(UserEntity userEntity) {

		return weatherRepository.findAllByUserByDateDesc(userEntity);
	}
	
	/**
	 *  //Get weather by User by group by name city by date ASC
	 */
	@Override
	public List<WeatherEntity> findAllByUserByDateAsc(UserEntity userEntity) {

		return weatherRepository.findAllByUserByDateAsc(userEntity);
	}
	
	/**
	 * URL get current weather by API
	 * @param nameCity
	 * @return
	 */
	private String urlGetWeather(String nameCity) {
		
		return weatherURL+weatherVersion+weatherCurrent+"q="+nameCity+"&APPID="+appID+"&units=imperial";
	}
	
	/**
	 * URL get forecast weather by name city
	 * @param nameCity
	 * @return
	 */
	private String urlGetForecast(String nameCity) {
		
		return weatherURL+weatherVersion+weatherForecast+"q="+nameCity+"&APPID="+appID+"&units=imperial";
	}
	
	/**
	 * URL get current location by lat and lon
	 * @param nameCity
	 * @return
	 */
	private String urlGetCurrentLocation(String lat, String lon) {
		
		return weatherURL+weatherVersion+weatherCurrent+"lat="+lat+"&lon="+lon+"&APPID="+appID+"&units=imperial";
	}
	
	/**
	 * Call API Weather By Name City
	 * 
	 * @param name
	 * @return WeatherEntity
	 */
	public WeatherEntity getWeatherByApi(String nameCity) {
		
		String URL = urlGetWeather(nameCity);
		RestTemplate restTemplate = new RestTemplate();
		CurrentWeatherDTO weatherDTO = restTemplate.getForObject(URL, CurrentWeatherDTO.class);

		return weatherConverter.convertToEntity(weatherDTO);
	}

	/**
	 * Get Info Weather At Current Location By Latitude And Longitude
	 * 
	 * @param lat
	 * @param lon
	 * @return CurrentWeatherEntity
	 */
	public CurrentWeatherEntity restCurWeather(String lat, String lon) {

		String URL = urlGetCurrentLocation(lat, lon);
		RestTemplate restTemplate = new RestTemplate();
		CurrentWeatherDTO weatherDTO = restTemplate.getForObject(URL, CurrentWeatherDTO.class);

		return weatherConverter.convertToCurWeather(weatherDTO);
	}

	/**
	 * Call API Forecast Weather By Name City
	 * 
	 * @param name
	 * @return DetailWeatherDTO
	 */
	public DetailsWeatherDTO foreCast(String nameCity) {

		String URL = urlGetForecast(nameCity);
		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(URL, DetailsWeatherDTO.class);
	}
}
