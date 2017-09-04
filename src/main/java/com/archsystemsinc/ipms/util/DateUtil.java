package com.archsystemsinc.ipms.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	
	public static Date getDateOnly(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    final long LENGTH_OF_DAY = 24*60*60*1000;
	    long millis = calendar.getTimeInMillis();
	    long offset = calendar.getTimeZone().getOffset(millis);
	    millis = millis - ((millis + offset) % LENGTH_OF_DAY);
	    return new Date(millis);
	}
	public static Date addDate(Date source, int days) {
		long millis = source.getTime();
		final long LENGTH_OF_DAY = 24*60*60*1000;
		millis = millis + (days * LENGTH_OF_DAY);
		return new Date(millis);
	}
	
	public static void main(String[] args) {
		System.out.println(getDateOnly(new Date()));
		System.out.println(addDate(getDateOnly(new Date()),10));
	}
}
