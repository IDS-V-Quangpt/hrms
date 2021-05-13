package com.hr.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String SHORT_FORMAT = "yyyy-MM-dd";
	
	public static String firstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);
        return formatter.format(cal.getTime());
    }
	
	public static String lastDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);
        return formatter.format(cal.getTime());
    }
	
	public static String formatTimeStamp(long date) {
		Timestamp ts = new Timestamp(date);
		SimpleDateFormat formatter = new SimpleDateFormat(SHORT_FORMAT);
		return formatter.format(ts.getTime());
	}
	
	public static DayOfWeek getWeek(String date) {
		
		LocalDate localDate = LocalDate.parse(date);
        DayOfWeek day = localDate.getDayOfWeek();
        return day;
	}
	
	public static String getHourAndMinute(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
		
	}
}
