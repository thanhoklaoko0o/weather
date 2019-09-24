package com.program.weather.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
import org.springframework.web.client.RestTemplate;

import com.program.weather.api.AdminApi;
import com.program.weather.api.WeatherApi;
import com.program.weather.converter.CurrentWeatherConverter;
import com.program.weather.dto.CurrentWeatherDTO;
import com.program.weather.entity.CurrentWeatherEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.repository.CurrentWeatherRepository;
import com.program.weather.service.impl.UserServiceImpl;
import com.program.weather.utils.CommonUtil;
import com.program.weather.utils.Constants;

@Controller
@RequestMapping("/home-weather")
public class HomeController {

	@Autowired
	private WeatherApi weatherApi;
	
	@Autowired
	CurrentWeatherConverter converter;
	
	@Autowired
	AdminApi adminApi;
	
	@Autowired
	CurrentWeatherRepository currentWeatherRepository;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	

	@GetMapping
	public String homeDefault(Model model, Principal principal) {
		UserEntity userEntity = userServiceImpl.findByUserName(principal.getName());
		List<CurrentWeatherEntity> lstWeather = weatherApi.findAllByUserEntities(userEntity);
		model.addAttribute("lstWeather", lstWeather);
		return "pageHome";
	}

	@GetMapping("/403")
	public String accessDenied(Model model, Principal principal) {
		if (principal != null) {
			String message = "Hi " + principal.getName() + "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);
		}
		return "403Page";
	}
	
	@GetMapping("/foreCast")
	public String foreCast5Day() {
		return "foreCast";
	}
	
	@GetMapping("/search-city")
	public String home(@RequestParam String name, ModelMap modelMap) {
		CurrentWeatherDTO currentWeather = weatherApi.searchWeather(name);
		String urlIMG = Constants.IMG_URL + currentWeather.getWeather().get(0).getIcon() + Constants.PNG;
		String curDate = CommonUtil.fomatDate();
		String C = CommonUtil.toCelsius(Double.parseDouble(currentWeather.getMain().getTemp()));
		String info = currentWeather.getWeather().get(0).getDescription();

		String gio = currentWeather.getWind().getSpeed();
		String doAm = currentWeather.getMain().getHumidity();
		String Hp = currentWeather.getMain().getPressure();

		String mota = gio + " m/s. " + doAm + "%, " + Hp + " hpa";

		modelMap.addAttribute("currentWeather", currentWeather);
		modelMap.addAttribute("urlIMG", urlIMG);
		modelMap.addAttribute("curDate", curDate);
		modelMap.addAttribute("C", C);
		modelMap.addAttribute("info", info);
		modelMap.addAttribute("mota", mota);
		return "pageHome";
	}
	
	@GetMapping("/save-weather")
	@ResponseBody
	public void saveWeather(@RequestParam String name) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String nameUser=authentication.getName();
		UserEntity userEntity = adminApi.getUser(nameUser);
	//	Long id = userEntity.getUserId();
		
		String URL = Constants.WEATHER_URL+name+Constants.APPID;
		RestTemplate restTemplate = new RestTemplate();
		CurrentWeatherDTO weatherDTO = restTemplate.getForObject(URL, CurrentWeatherDTO.class);
		CurrentWeatherEntity result = converter.convertToEntity(weatherDTO);
		result.setUserEntities(new HashSet<UserEntity>(Arrays.asList(userEntity)));
		
		result.setCreateBy(userEntity.getLastName()+" "+userEntity.getFirstName());
		currentWeatherRepository.save(result);
		
	} 
	

	
	

}
