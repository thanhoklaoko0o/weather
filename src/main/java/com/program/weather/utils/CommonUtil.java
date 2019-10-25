package com.program.weather.utils;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
/**
 * 
 * @author NgocHung
 *
 */
public class CommonUtil {

	/**
	 * 
	 * @param timeUTC
	 * @return time type HH:mm:ss
	 */
	public static String fomatHHmmss( int timeUTC) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Timestamp HHmmss = new Timestamp(timeUTC*1000);
		return sdf.format(HHmmss);
	}
}
