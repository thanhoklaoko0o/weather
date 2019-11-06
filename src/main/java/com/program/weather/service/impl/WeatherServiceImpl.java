package com.program.weather.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.program.weather.converter.WeatherConverter;
import com.program.weather.dto.display.CurrentWeatherLocationDTO;
import com.program.weather.dto.tranfer.CurrentWeatherDTO;
import com.program.weather.dto.tranfer.DetailsWeatherDTO;
import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.repository.WeatherRepository;
import com.program.weather.service.WeatherService;

/**
 * Business Logic Layer Weather Service
 * Process logic and call method from weatherrepository
 * @author USER
 *
 */
@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private WeatherRepository weatherRepository;

	@Autowired
	private WeatherConverter weatherConverter;

	@Autowired
	RestTemplate restTemplate;

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
	 * Get List Weather By USER
	 */
	@Override
	public List<WeatherEntity> findAllByUserEntity(UserEntity userEntity) {
		return weatherRepository.findAllByUserEntity(userEntity);
	}

	/**
	 * Get weather by User by group by name city by date DESC
	 */
	@Override
	public List<WeatherEntity> findAllByUserByDateDesc(Long userId) {
		return weatherRepository.findAllByUserByDateDesc(userId);
	}
	
	/**
	 * Get weather by User by group by name city by date ASC
	 */
	@Override
	public List<WeatherEntity> findAllByUserByDateAsc(Long userId) {
		return weatherRepository.findAllByUserByDateAsc(userId);
	}

	/**
	 * URL get current weather by API
	 * @param nameCity
	 * @return Url api weather by name city
	 */
	private StringBuilder urlApiGetWeather(String nameCity) {
		return urlApiWeather().append(weatherURL+weatherVersion+weatherCurrent+"q="+nameCity+"&APPID="+appID+"&units=metric");
	}

	/**
	 * URL get forecast weather by name city
	 * @param nameCity
	 * @return Url api forecast weather
	 */
	private StringBuilder urlApiGetForecast(String nameCity) {
		return urlApiWeather().append(weatherURL+weatherVersion+weatherForecast+"q="+nameCity+"&APPID="+appID+"&units=metric");
	}

	/**
	 * URL get current location by lat and lon
	 * @param nameCity
	 * @return Url api current location
	 */
	private StringBuilder urlApiGetCurrentLocation(String lat, String lon) {
		return urlApiWeather().append(weatherURL+weatherVersion+weatherCurrent+"lat="+lat+"&lon="+lon+"&APPID="+appID+"&units=metric");
	}

	/**
	 * Call API Weather By Name City
	 * 
	 * @param name
	 * @return WeatherEntity
	 */
	public WeatherEntity getWeatherByApi(String nameCity) {
		CurrentWeatherDTO weatherDTO = restTemplate.getForObject(urlApiGetWeather(nameCity).toString(), CurrentWeatherDTO.class);
		//convert weatherDTO to weatherEntity
		return weatherConverter.convertToEntity(weatherDTO);
	}

	/**
	 * Get Info Weather At Current Location By Latitude And Longitude
	 * 
	 * @param lat
	 * @param lon
	 * @return CurrentWeatherEntity
	 */
	public CurrentWeatherLocationDTO getWeatherCurWeather(String lat, String lon) {
		CurrentWeatherDTO weatherDTO = restTemplate.getForObject(urlApiGetCurrentLocation(lat, lon).toString(), CurrentWeatherDTO.class);
		//conver weatherDTO to current Weather
		return weatherConverter.convertToCurWeather(weatherDTO);
	}

	/**
	 * Call API Forecast Weather By Name City
	 * 
	 * @param name
	 * @return DetailWeatherDTO
	 */
	public DetailsWeatherDTO foreCast(String nameCity) {
		return restTemplate.getForObject(urlApiGetForecast(nameCity).toString(), DetailsWeatherDTO.class);
	}

	/**
	 * 
	 * @return
	 */
	private StringBuilder urlApiWeather() {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder;
	}
}
