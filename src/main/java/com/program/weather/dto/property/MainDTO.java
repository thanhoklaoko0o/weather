package com.program.weather.dto.property;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MainDTO {
	
		private String temp;
		private String pressure;
		private String humidity;
		private String temp_min;
		private String temp_max;
		
		
}
