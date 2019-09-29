package com.program.weather.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailsWeatherEntity {

	private String day;
	private String image;
	private String tempMin;
	private String tempMax;
	private String description;
	private String wind;
	private String humidity;
	private String pressure;
	private String clouds;
	
	
	public DetailsWeatherEntity(String day, String image, String tempMin, String tempMax, String description,
			String wind, String humidity, String pressure, String clouds) {
		super();
		this.day = day;
		this.image = image;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.description = description;
		this.wind = wind;
		this.humidity = humidity;
		this.pressure = pressure;
		this.clouds = clouds;
	}
	
	
	
	

}
