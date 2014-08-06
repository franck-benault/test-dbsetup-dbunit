package net.franckbenault.dbtest.sample;

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
	
	public static Date getTomorrow() {
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		return dt;		
	}
}
