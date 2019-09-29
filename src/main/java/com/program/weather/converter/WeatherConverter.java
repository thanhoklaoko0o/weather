package com.program.weather.converter;

import java.sql.Timestamp;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.program.weather.dto.CurrentWeatherDTO;
import com.program.weather.entity.WeatherEntity;

@Component
public class WeatherConverter {
	
	public WeatherEntity convertToEntity(CurrentWeatherDTO weatherDTO) {
		ModelMapper modelMapper = new ModelMapper();
		WeatherEntity result = modelMapper.map(weatherDTO, WeatherEntity.class);
		result.setIcon(weatherDTO.getWeather().get(0).getIcon());
		result.setNameCity(weatherDTO.getName());
		result.setIdCity(weatherDTO.getId());
		result.setDate(new Timestamp(System.currentTimeMillis()));
		result.setTemp(weatherDTO.getMain().getTemp());
		result.setDescription(weatherDTO.getWeather().get(0).getDescription());
		result.setWind(weatherDTO.getWind().getSpeed());
		result.setHumidity(weatherDTO.getMain().getHumidity());
		result.setPressure(weatherDTO.getMain().getPressure());
		
		return result;
	}
}
