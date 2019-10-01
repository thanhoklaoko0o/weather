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

		UserEntity userEntity = userServiceImpl.findByUserName(principal.getName());
		
		List<WeatherEntity> lstWeather = weatherServiceImpl.findDateTimeByUserGroupbyDateTimeAcs(userEntity.getUserId());
		List<WeatherEntity> listquery  = weatherServiceImpl.findDateTimeByUserGroupbyDateTimeDest(userEntity.getUserId());
		
		
		model.addAttribute("lstWeatherCity", listquery);
		model.addAttribute("lstWeather", lstWeather);
		
		return "user/pageHome";
	}

	/**
	 * get list weather by nameCity
	 * @param name
	 * @return
	 */
	@GetMapping("/show-more")
	@ResponseBody
	public List<WeatherEntity> showMoreCity(@RequestParam String name) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserEntity userEntity = userServiceImpl.findByUserName(authentication.getName());

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
	 * ForeCast 5 day of City
	 * 
	 * @param name
	 * @param model
	 * @return view : foreCast
	 */
	@GetMapping("/foreCast")
	public String foreCast5Day(@RequestParam String name, Model model) {
		//List contain forecast 5 day of city
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

		// list theo ten serach
		UserEntity userEntity = userServiceImpl.findByUserName(principal.getName());
		List<WeatherEntity> lstWeatherByName;
		// list weather by user
		List<WeatherEntity> lstWeather = weatherServiceImpl.findAllByUserEntities(userEntity);
		lstWeatherByName = lstWeather.stream().filter(curweather -> name.equalsIgnoreCase(curweather.getNameCity()))
				.collect(Collectors.toList());
		
		try {
			WeatherEntity weatherEntity = weatherServiceImpl.restJsonData(name);
			
			if(weatherEntity != null) {
				
				// lay thoi tiet hien tai cung ten Search trong db , Xy ly nut add va update
				WeatherEntity curWeather = lstWeatherByName.stream()
											.filter(weather -> CommonUtil.curTimeToString().equals(CommonUtil.formatToString(weather.getDate())))
											.findAny().orElse(null);
				if (curWeather != null) {
					modelMap.addAttribute("flag", "update");
				} else {
					modelMap.addAttribute("flag", "add");
				}
				
				modelMap.addAttribute("weatherSearch", weatherEntity);
				
			}
		} catch (Exception e) {
			
			modelMap.addAttribute("msgSearch", "City is not found !");
		}
		
		
		
		List<WeatherEntity> listquery = weatherServiceImpl.findDateTimeByUserGroupbyDateTimeDest(userEntity.getUserId());
		
		modelMap.addAttribute("lstWeatherCity", listquery);
		modelMap.addAttribute("lstWeather", lstWeather);
		
		

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
		
		return "redirect:?Delete_Successful_WeatherId="+id;
	}

	/**
	 * Update And Insert Weather City
	 * 
	 * @param action
	 * @param name
	 * @param principal
	 * @return view home + message add successful
	 */
	@GetMapping("/save-weather")
	public String saveWeather(@RequestParam String action, @RequestParam String name, Principal principal) {
		UserEntity userEntity = userServiceImpl.findByUserName(principal.getName());
		// lst weather by user
		List<WeatherEntity> lstByUser = weatherServiceImpl.findAllByUserEntities(userEntity);

		List<WeatherEntity> lstByUserByCity;
		// lay ra ds city theo iduser va nameCity
		lstByUserByCity = lstByUser.stream()
				.filter(weather -> name.trim().toLowerCase().equals(weather.getNameCity().trim().toLowerCase()))
				.collect(Collectors.toList());
		// kiem tra action
		if (action.equals("Add")) {
			// kiem tra truong hop record luu toi da 1 city la 3
			if (lstByUserByCity.size() < 3) {

				insertWeather(name, userEntity);
				return "redirect:?Add_Successfull_Name=" + name;
			} else {
				lstByUserByCity.sort((WeatherEntity w1, WeatherEntity w2) -> w1.getDate().compareTo(w2.getDate()));
				Optional<WeatherEntity> entitya = lstByUserByCity.stream().findFirst();

				weatherServiceImpl.deleteWeather(entitya.get());
				insertWeather(name, userEntity);
				return "redirect:?Add_Successfull_Name=" + name;
			}
		}
		lstByUserByCity.sort((WeatherEntity w1, WeatherEntity w2) -> w2.getDate().compareTo(w1.getDate()));
		Optional<WeatherEntity> entitya = lstByUserByCity.stream().findFirst();
		weatherServiceImpl.deleteWeather(entitya.get());
		insertWeather(name, userEntity);

		return "redirect:?Update_Successfull_Name=" + name;
	}

	/**
	 * Function insert commom for insert and update
	 * 
	 * @param name
	 * @param userEntity
	 */

	public void insertWeather(String name, UserEntity userEntity) {
		WeatherEntity result = weatherServiceImpl.restJsonData(name);
		result.setUserEntities(new HashSet<UserEntity>(Arrays.asList(userEntity)));
		result.setCreateBy(userEntity.getLastName() + " " + userEntity.getFirstName());
		weatherServiceImpl.saveWeather(result);
	}
	
	/**
	 * Search Ajax
	 * @param name
	 * @return
	 */
	@GetMapping("/searchajax")
	@ResponseBody
	public WeatherEntity searchAjax(@RequestParam String name ) {
		
		WeatherEntity weatherEntity = weatherServiceImpl.restJsonData(name);
	
		return weatherEntity;
	}

}
