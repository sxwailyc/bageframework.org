package com.bageframework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	public final static String FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

	public final static String SHORT_FORMAT_STRING = "yyyyMMdd";

	public static Date now() {
		return new Date();
	}

	public static Date str2Date(String dateString) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_STRING);
			return simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException("时间转化格式错误!" + "[dateString=" + dateString + "]" + "[FORMAT_STRING=" + FORMAT_STRING + "]");
		}
	}

	public static String date2String(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	public static String date2String(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_STRING);
		return simpleDateFormat.format(date);
	}

	public static String getDateStr() {
		Date date = now();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SHORT_FORMAT_STRING);
		return simpleDateFormat.format(date);

	}

	public static Date addTime(final Date startDate, final int minute) {
		long millis = startDate.getTime();
		millis = millis + (minute * 60L * 1000L);
		Date date = new Date(millis);
		return date;
	}

	public static Date addDate(final Date date, final int daynum) {
		int minute = daynum * 60 * 24;
		return addTime(date, minute);
	}

}
