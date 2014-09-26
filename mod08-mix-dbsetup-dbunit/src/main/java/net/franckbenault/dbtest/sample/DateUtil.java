package net.franckbenault.dbtest.sample;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	
	public static Date getYesterday() {
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, -1);
		dt = c.getTime();
		return dt;
	}
	
	public static String getYesterdayString() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		return dt.format(getYesterday());
	}

	public static String getTomorrowString() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		return dt.format(getTomorrow());
	}
	
	
	public static Date getTomorrow() {
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		return dt;		
	}
}
