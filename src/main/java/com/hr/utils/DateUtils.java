package com.hr.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String SHORT_FORMAT = "yyyy-MM-dd";
	private static final String FORMAT_HOURS = "HH:mm aa";
	private static final String HOUR_DURATION = "HH:mm";
	private static final int NUMBER_OF_LUNCH = 3600000;
	private static final String DEFAULT_HOUR = "00:00";
	private static final String FORMAT_HOUR_MINUTE = "%02d:%02d";

	public static String firstDayOfMonth(Date d) {

		Calendar cal = Calendar.getInstance();
		if (d == null) {
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH, 1);
		} else {
			cal.setTime(d);
		}

		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);

		return formatter.format(cal.getTime());
	}

	public static String lastDayOfMonth(Date d) {
		Calendar cal = Calendar.getInstance();
		if (d == null) {
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		} else {
			cal.setTime(d);
		}
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

		DateFormat dateFormat = new SimpleDateFormat(FORMAT_HOURS);
		String dateString = dateFormat.format(date).toString();

		return dateString;

	}

	public static String formatDuration(String startDate, String endDate) {

		SimpleDateFormat sdf = new SimpleDateFormat(HOUR_DURATION);

		Date startTime = null;
		Date endTime = null;
		try {
			startTime = sdf.parse(startDate);
			endTime = sdf.parse(endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		long total = (endTime.getTime() - startTime.getTime());

		long hours = TimeUnit.MILLISECONDS.toHours(total - NUMBER_OF_LUNCH);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(total) % 60;
		long seconds = TimeUnit.MILLISECONDS.toSeconds(total) % 60;
		long milliseconds = total % 1000;

		if (hours == -1) {
			return DEFAULT_HOUR;
		}

		return String.format(FORMAT_HOUR_MINUTE, hours, minutes, seconds, milliseconds);
	}
	
//	public static String calPaidLeave(String strDate) {
//		
//		SimpleDateFormat sdf = new SimpleDateFormat(HOUR_DURATION);
//
//		Date sDate = null;
//		try {
//			sDate = sdf.parse(strDate);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		LocalDateTime local = LocalDateTime.parse();
//
////		long hours = TimeUnit.MILLISECONDS.toHours(total);
////		long minutes = TimeUnit.MILLISECONDS.toMinutes(total) % 60;
////		long seconds = TimeUnit.MILLISECONDS.toSeconds(total) % 60;
////		long milliseconds = total % 1000;
////		
////		if (hours < 8) {
////			return "OK";
////		}
//		
//		return null;
//	}
	
//	public static void main(String[] args) {
//		calPaidLeave("06:50");
//	}
}
