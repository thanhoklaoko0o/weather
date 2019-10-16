package com.program.weather.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class CommonUtil {
	
	/**
	 * Convert temperature F --> C
	 * @param Fah
	 * @return
	 */
	public static  String toCelsius(double Fahrenheit) {
		NumberFormat formatter =new DecimalFormat("#0.00");
	    return  formatter.format((Fahrenheit - 32) * 5/9);
	    }
	
	/**
	 * 
	 * @param UTC
	 * @return
	 */
	public static String fomatHHmmss( int UTC) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Timestamp HHmmss = new Timestamp(UTC*1000);
		return sdf.format(HHmmss);
	}
	
	/**
	 * Display Name Month Day, Year
	 * @return
	 */
	public static String fomatDate(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMMM dd, yyyy");
		return simpleDateFormat.format(new Date());
	}
	
	/**
	 * Format TimeStamp to String yyyymmdd
	 * @param ts
	 * @return
	 */
	 public static String formatToString( Timestamp ts) {
		  String formattedDate = new SimpleDateFormat("yyyyMMdd").format(ts);
		  return formattedDate;
	  }
	 
	 /**
	  * Parse curTime to String 
	  * @return
	  */
	 public static String curTimeToString() {
		  Timestamp  ts= new Timestamp(System.currentTimeMillis());
		  String formattedDate = new SimpleDateFormat("yyyyMMdd").format(ts);
		  return formattedDate;
	  }
	 
	 /**
	  * convert localdate to timestamp
	  * @param ldt
	  * @return
	  */
	 public static Timestamp getDate(LocalDateTime ldt) {
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	String str = ldt.format(dtf);
	    	Timestamp ts = Timestamp.valueOf(str);
	        return ts;
	    }
	 
	 
	
	
}
