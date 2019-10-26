package com.program.weather.dto.tranfer;

import java.util.ArrayList;
import java.util.List;

import com.program.weather.dto.tranfer.property.CloudsDTO;
import com.program.weather.dto.tranfer.property.CoordDTO;
import com.program.weather.dto.tranfer.property.MainDTO;
import com.program.weather.dto.tranfer.property.SysDTO;
import com.program.weather.dto.tranfer.property.WeatherDTO;
import com.program.weather.dto.tranfer.property.WindDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 
 * @author NgocHung
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class CurrentWeatherDTO {
	private CoordDTO coord;
	private List<WeatherDTO> weather = new ArrayList<WeatherDTO>();
	private String base;
	private MainDTO main;
	private String visibility;
	private WindDTO wind;
	private CloudsDTO clouds;
	private String dt;
	private SysDTO sys;
	private String timezone;
	private String id;
	private String  name;
	private String cod;
}
