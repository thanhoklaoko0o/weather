package com.program.weather.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.program.weather.dto.display.DetailsWeatherForecastDTO;
import com.program.weather.dto.tranfer.DetailsWeatherDTO;
import com.program.weather.dto.tranfer.property.ListDetailDTO;
import com.program.weather.service.WeatherService;
import com.program.weather.utils.CommonUtil;

/**
 * 
 * @author Ngoc Hung
 *
 */
@Controller
@RequestMapping("/forecast-weather")
public class WeatherDetailsController {

	@Autowired
	private WeatherService weatherService;

	/**
	 * USER click nameCity to watch ForeCast 5 day of City ForeCast 5 day of City
	 * 
	 * @param name
	 * @param model
	 * @return view : foreCast
	 */
	@GetMapping
	public String foreCast5Day(@RequestParam String name, Model model) {
		try {
			// Get list forecast weather 5 day of city
			List<DetailsWeatherForecastDTO> lstForecast = getListDetails5Day(name);
			if(lstForecast != null) {
				model.addAttribute("lstForeCast", lstForecast);
				model.addAttribute("nameCity", name.toUpperCase());
				model.addAttribute("timeToday", Instant.now());
				return "user/foreCast";
			}
		} catch (Exception e) {
			return "redirect:home-weather?message_forecast=notfound";
		}
		return "";
	}

	/**
	 * get list detail 5 day from ListDTO
	 * @param listDetail
	 * @return list detail weather entity
	 */
	private List<DetailsWeatherForecastDTO> getListDetailsDTO(List<ListDetailDTO> listDetail){
		int SIZE_WEATHER_REPEAT = 8;
		List<DetailsWeatherForecastDTO> lstForecast = new ArrayList<DetailsWeatherForecastDTO>();
		for (int i = 0; i < listDetail.size(); i += SIZE_WEATHER_REPEAT) {
			lstForecast.add(new DetailsWeatherForecastDTO(i, listDetail.get(i).getDt_txt(),
							listDetail.get(i).getWeather().get(0).getIcon(),
							CommonUtil.toCelsius(Double.parseDouble(listDetail.get(i).getMain().getTemp_min())),
							CommonUtil.toCelsius(Double.parseDouble(listDetail.get(i).getMain().getTemp_max())),
							listDetail.get(i).getWeather().get(0).getDescription(),
							listDetail.get(i).getWind().getSpeed(),
							listDetail.get(i).getMain().getHumidity(),
							listDetail.get(i).getMain().getPressure(),
							listDetail.get(i).getClouds().getAll()));
		}
		return lstForecast;
	}

	/**
	 * Get list detail weather entity forecast
	 * @param nameCity
	 * @return list detailweatherEntity forecast
	 */
	private List<DetailsWeatherForecastDTO> getListDetails5Day(String nameCity){
		// Get list forecast weather from API
		DetailsWeatherDTO detailsWeatherDTO = weatherService.foreCast(nameCity);
		return getListDetailsDTO(detailsWeatherDTO.getList());
	}
}
