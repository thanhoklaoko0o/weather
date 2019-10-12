package com.program.weather.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
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
