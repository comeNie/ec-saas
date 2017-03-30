package com.yjg.ec.platform.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils {

	private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	private DateUtils() {

	}

    /**
     * 计算当天剩余时间（秒�?
     * @return
     */
	public static Long getFragmentInSeconds() {
	    return 24 * 60 * 60 - org.apache.commons.lang3.time.DateUtils.getFragmentInSeconds(Calendar.getInstance(), Calendar.DATE);
    }

	/**
	 * 得到本周周一
	 * 
	 * @return Date
	 */
	public static Date getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return c.getTime();
	}

	/**
	 * 输入日期计算其他日期
	 * 
	 * @param sourceDay
	 * @param calDays
	 * @return
	 */
	public static Date calDays(Date sourceDay, int calDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDay);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + calDays);
		return calendar.getTime();
	}

	public static String parseDate(Date date, String pattern) {
		if (date == null) {
			return null;
		}

		if (pattern == null) {
			throw new NullPointerException();
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

	public static String parseDate(Date date) {
		return parseDate(date, DEFAULT_DATE_PATTERN);
	}

	public static java.sql.Date strToSqlDate(String strDate) {
		String str = strDate;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date d = null;
		try {
			d = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		java.sql.Date date = new java.sql.Date(d.getTime());
		return date;
	}

	public static void main(String[] args) {
		Date monday = getMondayOfThisWeek();
		System.out.println(monday);

		Date calDay = calDays(monday, 6);
		System.out.println(calDay);

	}

}
