/*
 *(C) Copyright 2019
 *@author USER
 *@date   Oct 11, 2019	
 *@version 1.0
 *
 */

package com.program.weather.utils;
/**
 * define property are used common
 * @author USER
 *
 */
public class Constants {
	
	public static final Boolean ACTIVE 				 = true;
	public static final Boolean UN_ACTIVE			 = false;
	public static final String  ADMIN				 = "ROLE_ADMIN"; 
	public static final String  USER				 = "ROLE_USER"; 
	public static final String 	EX_PNG         		 = ".png";
	public static final String 	APP_ID          	 = "&APPID=2eca42b0aeb22ff4ed48f151002ea023&units=imperial";
	public static final String 	HOST_HTTP			 = "http://";
	public static final String 	DOMAIN				 = "openweathermap.org";
	public static final String	VER_WEATHER			 = "2.5/";
	public static final String 	WEATHER_URL			 = HOST_HTTP+"api."+DOMAIN+"/data/"+VER_WEATHER+"weather?q=";
	public static final String 	FORECAST_URL		 = HOST_HTTP+"api."+DOMAIN+"/data/"+VER_WEATHER+"forecast?q=";
	public static final String  URL_LAT     		 = HOST_HTTP+"api."+DOMAIN+"/data/"+VER_WEATHER+"weather?lat=";
	public static final String  URL_LON				 = "&lon=";
	public static final String	IMG_URL     		 = HOST_HTTP+DOMAIN+"/img/wn/";
	public static final String  MY_EMAIL			 = "ngochungcntthueo0oo0@gmail.com"; 
	public static final String  MY_PASSWORD		     = "NgocHungCoder2011";
	
	
	public static final String  QUERY_WEARTHER_DESC  =  "SELECT * \r\n" + 
														"FROM dbweather.user_weather a join dbweather.weatherinfo b on a.weather_id = b.weather_id\r\n" + 
														"where a.user_id=?1 and b.date \r\n" + 
														"			 in ( select max(date) \r\n" + 
														"			 FROM dbweather.user_weather a join dbweather.weatherinfo b on a.weather_id = b.weather_id\r\n" + 
														"			 where a.user_id=?1 group by b.name_city order by date desc ) \r\n" + 
														"group by b.name_city \r\n" + 
														"order by date desc ";
														
	
	public static final String  QUERY_WEARTHER_ASC   =  "SELECT * \r\n" + 
														"FROM dbweather.user_weather a join dbweather.weatherinfo b on a.weather_id = b.weather_id\r\n" + 
														"where a.user_id=?1 and b.date \r\n" + 
														"			 in ( select min(date) \r\n" + 
														"			 FROM dbweather.user_weather a join dbweather.weatherinfo b on a.weather_id = b.weather_id\r\n" + 
														"			 where a.user_id=?1 group by b.name_city order by date desc ) \r\n" + 
														"group by b.name_city \r\n" + 
														"order by date desc ";
	
	
	
}









