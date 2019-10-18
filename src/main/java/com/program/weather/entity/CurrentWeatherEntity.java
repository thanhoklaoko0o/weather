package com.program.weather.entity;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author Ngoc Hung
 *
 */
@Getter
@Setter
public class CurrentWeatherEntity {
	private String image;
	private String temp;
	private String cloudiness;
	private String wind;
	private String pressure;
	private String humidity;
	private String sunrise;
	private String sunset;
	private String geocoords;
}
