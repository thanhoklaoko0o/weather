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

import com.program.weather.dto.DetailsWeatherDTO;
import com.program.weather.dto.property.ListDetailDTO;
import com.program.weather.entity.DetailsWeatherEntity;
import com.program.weather.service.impl.WeatherServiceImpl;
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
	private WeatherServiceImpl weatherServiceImpl;

	/**
	 * USER click nameCity to watch ForeCast 5 day of City ForeCast 5 day of City
	 * 
	 * @param name
	 * @param model
	 * @return view : foreCast
	 */
	@GetMapping
	public String foreCast5Day(@RequestParam String name, Model model) {
		//Get list forecast weather 5 day of city
		List<DetailsWeatherEntity> lstForecast = getListDetails5Day(name);
		model.addAttribute("lstForeCast", lstForecast);
		model.addAttribute("nameCity", name.toUpperCase());
		model.addAttribute("timeToday", Instant.now());
		return "user/foreCast";
	}

	/**
	 * get list detail 5 day from ListDTO
	 * @param listDetail
	 * @return list detail weather entity
	 */
	private List<DetailsWeatherEntity> getListDetailsDTO(List<ListDetailDTO> listDetail){
		int SIZE_WEATHER_REPEAT = 8;
		List<DetailsWeatherEntity> lstForecast = new ArrayList<DetailsWeatherEntity>();
		for (int i = 0; i < listDetail.size(); i += SIZE_WEATHER_REPEAT) {
			lstForecast.add(new DetailsWeatherEntity(i, listDetail.get(i).getDt_txt(),
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
	private List<DetailsWeatherEntity> getListDetails5Day(String nameCity){
		DetailsWeatherDTO detailsWeatherDTO = weatherServiceImpl.foreCast(nameCity);
		return getListDetailsDTO(detailsWeatherDTO.getList());
	}
}
