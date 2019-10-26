package com.program.weather.dto.display;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author Ngoc Hung
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class DetailsWeatherForecastDTO {

	private int index;
	private Instant day;
	private String image;
	private String tempMin;
	private String tempMax;
	private String description;
	private String wind;
	private String humidity;
	private String pressure;
	private String clouds;
}
