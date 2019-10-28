package com.program.weather.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.program.weather.dto.display.CurrentWeatherLocationDTO;
import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.service.UserService;
import com.program.weather.service.WeatherService;
/**
 * 
 * 
 * @author Ngoc Hung
 *
 */
@Controller
@RequestMapping("/home-weather")
public class HomeController {

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private UserService userService;

	/**
	 * Page Home return list weather by user
	 * 
	 * @param model
	 * @param principal
	 * @return view pageHome
	 */
	@GetMapping
	public String homeDefault(Model model, Principal principal) {
		//Get list weather by User order by Date desc
		List<WeatherEntity> listWeatherByDateDest = weatherService
				.findAllByUserByDateDesc(getCurUserEntity(principal).getUserId());
		//Check list weather is empty, If is empty forward page Home 
		if (listWeatherByDateDest.isEmpty()) {
			model.addAttribute("msgListEmpty", "No city had been choosen !");
			return "user/pageHome";
		}
		return processHome(model, principal);
	}

	/**
	 * Get list weather by nameCity
	 * 
	 * @param name
	 * @return list wearther by User by Name city
	 */
	@GetMapping("/show-more")
	@ResponseBody
	public List<WeatherEntity> showMoreCity(@RequestParam String name, Principal principal) {
		// Filter list weather to get list weather by name city
		List<WeatherEntity> lstWeatherByCity = listWeatherByUserByNameCity(name, principal);
		// Order list by date Desc
		lstWeatherByCity.sort(( we1, we2) -> we2.getDate().compareTo(we1.getDate()));
		// Remove first object of List
		lstWeatherByCity.remove(0);
		return lstWeatherByCity;	/*List weather by name order by Date*/
	}

	/**
	 * Search current Weather City
	 * 
	 * @param name
	 * @param modelMap
	 * @param principal
	 * @return view Home
	 */
	@GetMapping("/search-city")
	public String searchWeather(@RequestParam String name, Model model, Principal principal) {
		// Get result search : weather entity
		getWeatherSearch(name, principal, model);
		// Forward about page home
		return processHome(model, principal);
	}

	/**
	 * Process Data when tranfer to View
	 * @param model
	 * @param principal
	 * @return url page Home and Data weather by User 
	 */
	private String processHome(Model model, Principal principal) {
		// Get list weather by User order by Date desc
		List<WeatherEntity> listWeatherByDateDest = weatherService
				.findAllByUserByDateDesc(getCurUserEntity(principal).getUserId());
		// Get list weather by User order by Date Asc
		List<WeatherEntity> listWeatherByDateAsc = weatherService
				.findAllByUserByDateAsc(getCurUserEntity(principal).getUserId());
		// Set list weather for model to View
		model.addAttribute("listWeatherDest", listWeatherByDateDest);
		model.addAttribute("listWeatherAsc",  listWeatherByDateAsc);
		// Title page
		model.addAttribute("pageTitle", "Home");
		return "user/pageHome";
	}

	/**
	 * get result search 
	 * @param nameCity
	 * @param principal
	 * @param modelMap
	 */
	private void getWeatherSearch(String nameCity, Principal principal, Model model) {
		try {
			// Get weather by rest api
			WeatherEntity weatherEntity = weatherService.getWeatherByApi(nameCity);
			// Get list weather by user by name city
			List<WeatherEntity> listByNameCity = listWeatherByUserByNameCity(nameCity, principal);
			// Check list weather is empty, show button Add in View when search finished
			if (listByNameCity.isEmpty()) {
				model.addAttribute("addButton", " ");
			}
			model.addAttribute("weatherSearch", weatherEntity);
		} catch (Exception e) {
			// Name City search is not found
			model.addAttribute("msgSearch", "City is not found !");
		}
	}

	@GetMapping("/forecast-current")
	@ResponseBody
	public CurrentWeatherLocationDTO forecastCurrentWeather(@RequestParam String lat, @RequestParam String lon) {
		return weatherService.getWeatherCurWeather(lat, lon);
	}

	/**
	 * Delete weather by idweather
	 * 
	 * @param id
	 */
	@GetMapping("/deleteWeather")
	public String deleteWeatherUser(@RequestParam Long id) {
		weatherService.deleteWeatherById(id);
		return "redirect:?Delete_Successful_WeatherId=" + id;
	}

	/**
	 * Insert Weather City in DB by USER
	 * 
	 * @param action
	 * @param name
	 * @param principal
	 * @return view home + message add successful
	 */
	@GetMapping("/save-weather")
	public String saveWeather(@RequestParam String name, Principal principal) {
		// Get all weather by User and nameCity
		List<WeatherEntity> lstByUserByCity = listWeatherByUserByNameCity(name, principal);
		// check condition max record = 3
		if (lstByUserByCity.size() < 3) {
			// insert weather in DB
			return insertWeather(name, getCurUserEntity(principal));
		}
		// record of nameCity in DB = 3 , remove weather have oldest day
		lstByUserByCity.sort(( w1,  w2) -> w1.getDate().compareTo(w2.getDate()));
		// then sort list weather By Date ASC , Get weather first and delete it
		weatherService.deleteWeatherById(lstByUserByCity.get(0).getWeatherId());
		//Add weather in DB
		return insertWeather(name, getCurUserEntity(principal));
	}

	/**
	 * update info weather city in a Day
	 * @param name
	 * @param principal
	 * @return view Home 
	 */
	@GetMapping("/update-weather")
	public String updateWeather(@RequestParam String name, Principal principal) {
		// Get list weather by User and nameCity
		List<WeatherEntity> lstByUserByCity = listWeatherByUserByNameCity(name, principal);
		//Sort list weather By Date DESC
		lstByUserByCity.sort((WeatherEntity w1, WeatherEntity w2) -> w2.getDate().compareTo(w1.getDate()));
		//Get weather first and delete it 
		weatherService.deleteWeatherById(lstByUserByCity.get(0).getWeatherId());
		//Update Weather in DB
		return insertWeather(name, getCurUserEntity(principal));
	}

	/**
	 * Error when user have role to access page
	 * 
	 * @param model
	 * @param principal
	 * @return view : 403
	 */
	@GetMapping("/403")
	public String accessDenied(Model model, Principal principal) {
		if (principal != null) {
			UserEntity userEntity = getCurUserEntity(principal);
			String message = "Hi : " + userEntity.getFirstName() + " " + userEntity.getLastName() + " !"
									+ "<br> You do not have permission to access this page.";
			model.addAttribute("message", message);
		}
		// Title page
		model.addAttribute("pageTitle", "Access Denied");
		return "error/403Page";
	}

	/**
	 * Get list weather by User and nameCity
	 * 
	 * @param name
	 * @param principal
	 * @return list weather by user by name city
	 */
	private List<WeatherEntity> listWeatherByUserByNameCity(String nameCity, Principal principal) {
		// Get list weather by User 
		List<WeatherEntity> lstByUser 		= weatherService.findAllByUserEntity(getCurUserEntity(principal));
		// Get list weather by User by Name City
		List<WeatherEntity> lstByUserByCity = lstByUser.stream()
												.filter(weather -> nameCity.equalsIgnoreCase(weather.getNameCity()))
												.collect(Collectors.toList());
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
		return userService.findByUserName(principal.getName());
	}

	/**
	 * Function Insert Common for method save-weather and update-weather
	 * 
	 * @param name
	 * @param view home
	 */
	private String insertWeather(String nameCity, UserEntity userEntity) {
		// Get weather entity from rest API
		WeatherEntity result = weatherService.getWeatherByApi(nameCity);
		result.setUserEntity(userEntity);
		//Add weather in DB
		weatherService.saveWeather(result);
		//forward url page Home
		return "redirect:?Action_Successfull_Name=" + nameCity;
	}
}
