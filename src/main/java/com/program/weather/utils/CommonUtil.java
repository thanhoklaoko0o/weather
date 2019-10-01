package com.program.weather.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;


public class CommonUtil {
	
	/**
	 * Convert temp F --> C
	 * @param Fah
	 * @return
	 */
	public static  String toCelsius(double Fah) {
		NumberFormat formatter =new DecimalFormat("#0.00");
	    return  formatter.format((Fah - 32) * 5/9);
	    }
	
	/**
	 * Format Key Search
	 * @param s
	 * @return
	 */
	public static String removeAccent(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replace('đ', 'd').replace('Đ','D').replaceAll("\\s+","");
	}
	
	/**
	 * Display Name Month Day, Yeart
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
