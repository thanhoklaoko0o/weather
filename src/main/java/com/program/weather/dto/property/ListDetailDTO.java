package com.program.weather.dto.property;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class ListDetailDTO {
	
	private Instant        	 dt_txt;
	private MainDTO 		 main;
	private List<WeatherDTO> weather = new ArrayList<WeatherDTO>();
	private CloudsDTO 		 clouds;
	private WindDTO 		 wind;
	
	
	@JsonProperty("dt")
	public Instant  getDt_txt() {
		return dt_txt;
	}
	
}
