package com.hr.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtils {

	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String SHORT_FORMAT = "yyyy-MM-dd";
	private static final String FORMAT_HOURS = "HH:mm";
	private static final String HOUR_DURATION = "HH:mm";
	private static final int NUMBER_OF_LUNCH = 3600000;
	private static final String DEFAULT_HOUR = "00:00";
	private static final String FORMAT_HOUR_MINUTE = "%02d:%02d";
	private static final String DEFAULT_HOURS = "08:00";
	private static final String START_HOURS = "09:00";
	private static final String END_HOURS = "18:00";

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

	public static String getWeek(String date) {

		LocalDate localDate = LocalDate.parse(date);
		DayOfWeek day = localDate.getDayOfWeek();
		return day.getDisplayName(TextStyle.SHORT, Locale.US);
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
		if (total == 0) {
			return " ";
		}

		long hours = TimeUnit.MILLISECONDS.toHours(total - NUMBER_OF_LUNCH);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(total) % 60;

		if (hours == -1) {
			return DEFAULT_HOUR;
		}

		return String.format(FORMAT_HOUR_MINUTE, hours, minutes);
	}

	public static String hoursExtend(String sDate) {

		if (sDate.equals(" ")) {
			return " ";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(HOUR_DURATION);

		Date dtWork = null;
		Date dtWorkDefault = null;
		try {
			dtWork = sdf.parse(sDate);
			dtWorkDefault = sdf.parse(DEFAULT_HOURS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		long total = (dtWork.getTime() - dtWorkDefault.getTime());
		long hours = TimeUnit.MILLISECONDS.toHours(total);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(total) % 60;

		if ((hours == -1) || hours <= 8 && minutes <= 0) {
			return " ";
		}

		return String.format(FORMAT_HOUR_MINUTE, hours, minutes);
	}

	public static String calPaidLeave(String startDate, String endDate) {

		if ("".equals(startDate) || "".equals(endDate)) {
			return " ";
		}
		
		if (endDate.equals(startDate)) {
			return " ";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(HOUR_DURATION);

		Date startTime = null;
		Date endTime = null;
		Date dateBefore = null;
		Date dateAfter = null;
		try {
			startTime = sdf.parse(startDate);
			endTime = sdf.parse(endDate);
			dateBefore = sdf.parse(START_HOURS);
			dateAfter = sdf.parse(END_HOURS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar calStartTime = Calendar.getInstance();
		calStartTime.setTime(startTime);

		Calendar calEndTime = Calendar.getInstance();
		calEndTime.setTime(endTime);
		
		long hours = 0l;
		long minutes = 0l;

		if (calStartTime.get(Calendar.HOUR_OF_DAY) >= 9 && calStartTime.get(Calendar.HOUR_OF_DAY) < 12) {
			long totalStart = (startTime.getTime() - dateBefore.getTime());

			long hoursStart = TimeUnit.MILLISECONDS.toHours(totalStart);
			long minutesStart = TimeUnit.MILLISECONDS.toMinutes(totalStart) % 60;
			
			hours = hoursStart;
			minutes = minutesStart;
			
		}
		
		if (calEndTime.get(Calendar.HOUR_OF_DAY) < 18 && calEndTime.get(Calendar.HOUR_OF_DAY) >= 13) {
			
			long totalEnd = (dateAfter.getTime() - endTime.getTime());

			long hoursEnd = TimeUnit.MILLISECONDS.toHours(totalEnd);
			long minutesEnd = TimeUnit.MILLISECONDS.toMinutes(totalEnd) % 60;
			
			hours += hoursEnd;
			minutes+= minutesEnd;
		}
		
		if (hours == 0 && minutes == 0) {
			return " ";
		}

		return String.format(FORMAT_HOUR_MINUTE, hours, minutes);
	}
	
	public static String workingTime(String startDate, String endDate) {
		
		if ("".equals(startDate) || "".equals(endDate)) {
			return " ";
		}
		
		if (endDate.equals(startDate)) {
			return " ";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(HOUR_DURATION);

		Date startTime = null;
		Date endTime = null;
		try {
			startTime = sdf.parse(startDate);
			endTime = sdf.parse(endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Calendar calStartTime = Calendar.getInstance();
		calStartTime.setTime(startTime);
		
		Calendar calEndTime = Calendar.getInstance();
		calEndTime.setTime(endTime);
		
		long total = (endTime.getTime() - startTime.getTime());
		long hours = TimeUnit.MILLISECONDS.toHours(total - NUMBER_OF_LUNCH);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(total) % 60;

		if (hours == -1 || minutes < 0) {
			return " ";
		}
		
		if (hours > 8) {
			return "08:00";
		}
		
		return String.format(FORMAT_HOUR_MINUTE, hours, minutes);
	}
	
	
	public static void main(String[] args) {
		calPaidLeave("18:07", "18:16");
	}
}
