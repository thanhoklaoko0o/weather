package com.program.weather.dto.property;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class ListDetailDTO {
	
	private String dt_txt;
	private MainDTO main;
	private List<WeatherDTO> weather = new ArrayList<WeatherDTO>();
	CloudsDTO clouds;
	WindDTO wind;
}
