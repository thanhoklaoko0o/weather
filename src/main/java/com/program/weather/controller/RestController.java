package com.program.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.repository.WeatherRepository;
import com.program.weather.service.impl.UserServiceImpl;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

	@Autowired
	WeatherRepository weatherRepository;
	@Autowired
	UserServiceImpl userServiceImpl;

	@GetMapping("/getall/{id}")
	public List<WeatherEntity> getAllMax(@PathVariable Long id) {

		return weatherRepository.findAllByUserByDateDesc(userServiceImpl.findByUserId(id));
	}

	@GetMapping("/getalla/{id}")
	public List<WeatherEntity> getAllMaxa(@PathVariable Long id) {

		return weatherRepository.findAllByUserByDateAsc(userServiceImpl.findByUserId(id));
	}

	@GetMapping("/deleteWeather/{id}")
	public void deleteweatherbyid(@PathVariable Long id) {

		weatherRepository.deleteById(id);
	}

}
