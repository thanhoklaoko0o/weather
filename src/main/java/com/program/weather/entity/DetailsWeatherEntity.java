package com.program.weather.entity;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 
 * @author Ngoc Hung
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailsWeatherEntity {
	
	private int 	 index;
	private Instant  day;
	private String   image;
	private String   tempMin;
	private String   tempMax;
	private String   description;
	private String   wind;
	private String   humidity;
	private String   pressure;
	private String   clouds;
	
}
