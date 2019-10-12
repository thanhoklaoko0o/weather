package com.program.weather.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.converter.WeatherConverter;
import com.program.weather.dto.CurrentWeatherDTO;
import com.program.weather.dto.DetailsWeatherDTO;
import com.program.weather.entity.CurrentWeatherEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.repository.WeatherRepository;
import com.program.weather.service.WeatherService;
import com.program.weather.utils.Constants;

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
	 * Call API Weather By Name City
	 * 
	 * @param name
	 * @return WeatherEntity
	 */
	public WeatherEntity getWeatherByApi(String nameCity) {
		
		String URL = Constants.WEATHER_URL + nameCity + Constants.APP_ID;
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

		String URL = Constants.URL_LAT + lat + Constants.URL_LON + lon + Constants.APP_ID;
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

		String URL = Constants.FORECAST_URL + nameCity + Constants.APP_ID;
		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(URL, DetailsWeatherDTO.class);
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
}
