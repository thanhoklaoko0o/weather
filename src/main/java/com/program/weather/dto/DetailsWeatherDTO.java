package com.program.weather.dto;

import java.util.ArrayList;
import java.util.List;

import com.program.weather.dto.property.ListDetailDTO;

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
public class DetailsWeatherDTO {
	List<ListDetailDTO> list = new ArrayList<ListDetailDTO>();
}
