package com.program.weather.dto;

import java.util.ArrayList;
import java.util.List;

import com.program.weather.dto.property.CloudsDTO;
import com.program.weather.dto.property.CoordDTO;
import com.program.weather.dto.property.MainDTO;
import com.program.weather.dto.property.SysDTO;
import com.program.weather.dto.property.WeatherDTO;
import com.program.weather.dto.property.WindDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CurrentWeatherDTO {
	
	private CoordDTO    	 coord;
	private List<WeatherDTO> weather = new ArrayList<WeatherDTO>();
	private String   		 base;
	private MainDTO    		 main;
	private String   		 visibility;
	private WindDTO     	 wind;
	private CloudsDTO   	 clouds;
	private String   		 dt;
	private SysDTO      	 sys;
	private String  		 timezone;
	private String   		 id;
	private String   		 name;
	private String   		 cod;
	
}
