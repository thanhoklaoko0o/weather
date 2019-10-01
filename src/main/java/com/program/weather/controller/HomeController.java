package com.program.weather.controller;

import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.program.weather.dto.DetailsWeatherDTO;
import com.program.weather.entity.DetailsWeatherEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.service.impl.UserServiceImpl;
import com.program.weather.service.impl.WeatherServiceImpl;
import com.program.weather.utils.CommonUtil;
import com.program.weather.utils.Constants;

/**
 * 
 * 
 * @author USER
 *
 */
@Controller
@RequestMapping("/home-weather")
public class HomeController {

	@Autowired
	private WeatherServiceImpl weatherServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;

	/**
	 * Page Home return list weather by user
	 * 
	 * @param model
	 * @param principal
	 * @return view pageHome
	 */
	@GetMapping
	public String homeDefault(Model model, Principal principal) {
		// Get data weather city By UserID
		UserEntity userEntity = userServiceImpl.findByUserName(principal.getName());
		List<WeatherEntity> listWeatherByDateAsc = weatherServiceImpl
				.findDateTimeByUserGroupbyDateTimeAcs(userEntity.getUserId());
		List<WeatherEntity> listWeatherByDateDest = weatherServiceImpl
				.findDateTimeByUserGroupbyDateTimeDest(userEntity.getUserId());

		model.addAttribute("listWeatherDest", listWeatherByDateDest);
		model.addAttribute("listWeatherAsc", listWeatherByDateAsc);

		return "user/pageHome";
	}

	/**
	 * get list weather by nameCity
<<<<<<< HEAD
	 * 
=======
>>>>>>> c8ba72f0d214a3da1b2e79a2515f4003c0f4e15e
	 * @param name
	 * @return
	 */
	@GetMapping("/show-more")
	@ResponseBody
	public List<WeatherEntity> showMoreCity(@RequestParam String name) {
		// Get User to get List weather by user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserEntity userEntity = userServiceImpl.findByUserName(authentication.getName());

		// Filter list weather to get list weather by name city
		List<WeatherEntity> lstWeather = weatherServiceImpl.findAllByUserEntities(userEntity);
		lstWeather.sort((WeatherEntity we1, WeatherEntity we2) -> we2.getDate().compareTo(we1.getDate()));
		List<WeatherEntity> lstWeatherByCity = lstWeather.stream().filter(we -> we.getNameCity().equalsIgnoreCase(name))
				.collect(Collectors.toList());
		lstWeatherByCity.remove(0);

		return lstWeatherByCity;
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

			UserEntity userEntity = userServiceImpl.findByUserName(principal.getName());

			String message = "Hi " + userEntity.getFirstName() + " " + userEntity.getLastName() + " !"
					+ "<br> You do not have permission to access this page.";
			model.addAttribute("message", message);
		}

		return "error/403Page";
	}

	/**
	 * USER click nameCity to watch ForeCast 5 day of City
	 * ForeCast 5 day of City
	 * 
	 * @param name
	 * @param model
	 * @return view : foreCast
	 */
	@GetMapping("/foreCast")
	public String foreCast5Day(@RequestParam String name, Model model) {
		// List contain forecast 5 day of city
		List<DetailsWeatherEntity> lstForCast = new ArrayList<DetailsWeatherEntity>();
		DetailsWeatherDTO detailsWeatherDTO = weatherServiceImpl.foreCast(name);

		for (int i = 0; i < 40; i = i + 8) {
			
			lstForCast.add(
				 	new DetailsWeatherEntity(i,detailsWeatherDTO.getList().get(i).getDt_txt(),
					Constants.IMG_URL+detailsWeatherDTO.getList().get(i).getWeather().get(0).getIcon()+Constants.PNG,
					CommonUtil.toCelsius(Double.parseDouble(detailsWeatherDTO.getList().get(i).getMain().getTemp_min())),
					CommonUtil.toCelsius(Double.parseDouble(detailsWeatherDTO.getList().get(i).getMain().getTemp_max())),
					detailsWeatherDTO.getList().get(i).getWeather().get(0).getDescription(),
					detailsWeatherDTO.getList().get(i).getWind().getSpeed(),
					detailsWeatherDTO.getList().get(i).getMain().getHumidity(),
					detailsWeatherDTO.getList().get(i).getMain().getPressure(),
					detailsWeatherDTO.getList().get(i).getClouds().getAll()));
		}

		model.addAttribute("lstForeCast", lstForCast);
		model.addAttribute("nameCity", name.toUpperCase());
		model.addAttribute("timeToday", Instant.now());
		
		return "user/foreCast";
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
	public String searchWeather(@RequestParam String name, ModelMap modelMap, Principal principal) {

		// Get data weather city by ID User
		UserEntity userEntity = userServiceImpl.findByUserName(principal.getName());
		List<WeatherEntity> listWeatherByDateDest = weatherServiceImpl
				.findDateTimeByUserGroupbyDateTimeDest(userEntity.getUserId());
		List<WeatherEntity> listWeatherByDateAsc  = weatherServiceImpl
				.findDateTimeByUserGroupbyDateTimeAcs(userEntity.getUserId());

		// catch error if result search return null
		try {

			WeatherEntity weatherEntity = weatherServiceImpl.restJsonData(name);
			modelMap.addAttribute("weatherSearch", weatherEntity);

		} catch (Exception e) {

			modelMap.addAttribute("msgSearch", "City is not found !");
		}
			
		modelMap.addAttribute("listWeatherDest", listWeatherByDateDest);
		modelMap.addAttribute("listWeatherAsc" , listWeatherByDateAsc);

		return "user/pageHome";
	}

	/**
	 * Delete weather by idweather
	 * 
	 * @param id
	 */
	@GetMapping("/deleteWeather")
	public String deleteWeatherUser(@RequestParam Long id) {

		weatherServiceImpl.deleteWeather(id);
		return "redirect:?Delete_Successful_WeatherId=" + id;
	}

	/**
	 * Insert Weather City in DB by USER
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
			insertWeather(name, getCurUserEntity(principal));
			return "redirect:?Add_Successfull_Name=" + name;
		}

		// record of nameCity in DB = 3 , remove weather have oldest day
		lstByUserByCity.sort((WeatherEntity w1, WeatherEntity w2) -> w1.getDate().compareTo(w2.getDate()));

		// then sort list weather By Date ASC , Get weather first and delete it
		Optional<WeatherEntity> entityWeather = lstByUserByCity.stream().findFirst();
		weatherServiceImpl.deleteWeather(entityWeather.get());
		insertWeather(name, getCurUserEntity(principal));

		return "redirect:?Add_Successfull_Name=" + name;
	}

	@GetMapping("/update-weather")
	public String updateWeather(@RequestParam String name, Principal principal) {

		// Get all weather by User and nameCity
		List<WeatherEntity> lstByUserByCity = listWeatherByUserByNameCity(name, principal);

		// then sort list weather By Date DESC , Get weather first and delete it to
		// update
		lstByUserByCity.sort((WeatherEntity w1, WeatherEntity w2) -> w2.getDate().compareTo(w1.getDate()));
		Optional<WeatherEntity> entitya = lstByUserByCity.stream().findFirst();
		weatherServiceImpl.deleteWeather(entitya.get());
		insertWeather(name, getCurUserEntity(principal));

		return "redirect:?Update_Successfull_Name=" + name;
	}

	/**
	 * Get all weather by User and nameCity
	 * 
	 * @param name
	 * @param principal
	 * @return
	 */
	public List<WeatherEntity> listWeatherByUserByNameCity(String name, Principal principal) {
		// Get all weather by User and nameCity
		List<WeatherEntity> lstByUser = weatherServiceImpl.findAllByUserEntities(getCurUserEntity(principal));
		List<WeatherEntity> lstByUserByCity = lstByUser.stream()
				.filter(weather -> name.equalsIgnoreCase(weather.getNameCity())).collect(Collectors.toList());
		return lstByUserByCity;
	}

	/**
	 * Get UserEntity when USER login successfully
	 * 
	 * @param principal
	 * @return
	 */
	public UserEntity getCurUserEntity(Principal principal) {

		return userServiceImpl.findByUserName(principal.getName());
	}

	/**
	 * Function Insert Common for method save-weather and update-weather
	 * 
	 * @param name
	 * @param userEntity
	 */
	public void insertWeather(String name, UserEntity userEntity) {

		WeatherEntity result = weatherServiceImpl.restJsonData(name);
		// Set property weather and insert
		result.setUserEntities(new HashSet<UserEntity>(Arrays.asList(userEntity)));
		result.setCreateBy(userEntity.getLastName() + " " + userEntity.getFirstName());

		weatherServiceImpl.saveWeather(result);
	}

}
