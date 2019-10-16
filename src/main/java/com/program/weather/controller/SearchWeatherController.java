package com.program.weather.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.service.impl.UserServiceImpl;
import com.program.weather.service.impl.WeatherServiceImpl;

/**
 * 
 * @author Ngoc Hung
 *
 */
@Controller
public class SearchWeatherController {
	
	@Autowired
	private WeatherServiceImpl weatherServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	/**
	 * Search current Weather City
	 * 
	 * @param name
	 * @param modelMap
	 * @param principal
	 * @return view Home
	 */
	@GetMapping("/search-city")
	public String searchWeather(@RequestParam String name, ModelMap modelMap, Principal principal) {

		getWeatherSearch(name, principal, modelMap);

		List<WeatherEntity> listWeatherByDateDest = weatherServiceImpl
				.findAllByUserByDateDesc(getCurUserEntity(principal));
		List<WeatherEntity> listWeatherByDateAsc  = weatherServiceImpl
				.findAllByUserByDateAsc(getCurUserEntity(principal));

		modelMap.addAttribute("listWeatherDest", listWeatherByDateDest);
		modelMap.addAttribute("listWeatherAsc",  listWeatherByDateAsc);

		return "user/pageHome";
	}
	
	/**
	 * get result search 
	 * @param nameCity
	 * @param principal
	 * @param modelMap
	 */
	private void getWeatherSearch(String nameCity, Principal principal, ModelMap modelMap) {
		
		try {

			WeatherEntity		 weatherEntity = weatherServiceImpl.getWeatherByApi(nameCity);
			List<WeatherEntity> listByNameCity = listWeatherByUserByNameCity(nameCity, principal);
			//check exsist list weather by user 
			if (listByNameCity.isEmpty()) {

				modelMap.addAttribute("checkButton", " ");
			}

			modelMap.addAttribute("weatherSearch", weatherEntity);

		} catch (Exception e) {

			modelMap.addAttribute("msgSearch", "City is not found !");
		}
		
	}
	
	/**
	 * Get all weather by User and nameCity
	 * 
	 * @param name
	 * @param principal
	 * @return
	 */
	private List<WeatherEntity> listWeatherByUserByNameCity(String nameCity, Principal principal) {
		// Get all weather by User and nameCity
		List<WeatherEntity> lstByUser 		= weatherServiceImpl.findAllByUserEntity(getCurUserEntity(principal));
		List<WeatherEntity> lstByUserByCity = lstByUser.stream()
				.filter(weather -> nameCity.equalsIgnoreCase(weather.getNameCity())).collect(Collectors.toList());

		return lstByUserByCity;
	}

	/*
	 * Get UserEntity when USER login successfully
	 * 
	 * @param principal
	 * 
	 * @return
	 */
	private UserEntity getCurUserEntity(Principal principal) {

		return userServiceImpl.findByUserName(principal.getName());
	}

}
