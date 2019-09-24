package com.program.weather.dto;

import java.util.ArrayList;
import java.util.List;

public class CurrentWeatherDTO {
	
	private CoordDTO    coord;
	private List<WeatherDTO> weather = new ArrayList<WeatherDTO>();
	private String   base;
	private MainDTO     main;
	private String   visibility;
	private WindDTO     wind;
	private CloudsDTO   clouds;
	private String   dt;
	private SysDTO      sys;
	private String   timezone;
	private String   id;
	private String   name;
	private String   cod;
	
	
	public List<WeatherDTO> getWeather() {
		return weather;
	}
	public void setWeather(List<WeatherDTO> weather) {
		this.weather = weather;
	}
	public CoordDTO getCoord() {
		return coord;
	}
	public void setCoord(CoordDTO coord) {
		this.coord = coord;
	}
	
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public MainDTO getMain() {
		return main;
	}
	public void setMain(MainDTO main) {
		this.main = main;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public WindDTO getWind() {
		return wind;
	}
	public void setWind(WindDTO wind) {
		this.wind = wind;
	}
	public CloudsDTO getClouds() {
		return clouds;
	}
	public void setClouds(CloudsDTO clouds) {
		this.clouds = clouds;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public SysDTO getSys() {
		return sys;
	}
	public void setSys(SysDTO sys) {
		this.sys = sys;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	
	
	
}
