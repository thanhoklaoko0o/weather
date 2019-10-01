package com.program.weather.converter;

import java.sql.Timestamp;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.program.weather.dto.CurrentWeatherDTO;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.utils.CommonUtil;

@Component
public class WeatherConverter {
	
	/**
	 * Convert DTO to Entity
	 * @param weatherDTO
	 * @return Weather Entity
	 */
	public WeatherEntity convertToEntity(CurrentWeatherDTO weatherDTO) {
		
		ModelMapper modelMapper = new ModelMapper();
		WeatherEntity result = modelMapper.map(weatherDTO, WeatherEntity.class);
		
		// set property DTO to Entity
		result.setIcon(weatherDTO.getWeather().get(0).getIcon());
		result.setNameCity(weatherDTO.getName());
		result.setIdCity(weatherDTO.getId());
		result.setDate(new Timestamp(System.currentTimeMillis()));
		result.setTemp(CommonUtil.toCelsius(Double.parseDouble(weatherDTO.getMain().getTemp())));
		result.setDescription(weatherDTO.getWeather().get(0).getDescription());
		result.setWind(weatherDTO.getWind().getSpeed());
		result.setHumidity(weatherDTO.getMain().getHumidity());
		result.setPressure(weatherDTO.getMain().getPressure());
		
		return result;
	}
}
