package com.program.weather.utils;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
/**
 * 
 * @author NgocHung
 *
 */
public class CommonUtil {
	/**
	 * Convert temperature F --> C
	 * @param Fah
	 * @return temprature C
	 */
	public static String toCelsius(double Fahrenheit) {
		NumberFormat formatter =new DecimalFormat("#0.00");
		return formatter.format((Fahrenheit - 32) * 5/9);
	}

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
