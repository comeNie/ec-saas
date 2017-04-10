package com.yjg.ec.platform.erp.web.auth.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {

	/**
	 * 日期时间格式
	 */
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 时间格式
	 */
	public static final String TIME_FORMAT = "HHmmssSSS";
	/**
	 * 日期格式
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * EDW notify日期格式
	 */
	public static final String EDW_NOTIFY_DATE_FORMAT = "yyyyMMdd";

	/**
	 * 添加天数 method: DateUtil addDays
	 * 
	 * @param date
	 * @param days
	 * @return Date 创建日期： 2012-2-23 Copyright(C) 2012, by YJH.
	 */
	public static Date addDays(Date date, int days) {
		Date newDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance(Locale.getDefault());
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, days);
			newDate = calendar.getTime();
		}
		return newDate;
	}

	/**
	 * 格式化日期 method: DateUtil format
	 * 
	 * @param date
	 * @param dateFormats
	 * @return String 创建日期： 2012-2-23 Copyright(C) 2012, by YJH.
	 */
	public static String format(Date date, String dateFormats) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormats);
		return sdf.format(date);
	}

	/**
	 * 把日期格式化成字符串 method: DateUtil date2String
	 * 
	 * @param date
	 * @return String 创建日期： 2012-3-1 Copyright(C) 2012, by YJH.
	 */
	public static String date2String(Date date) {
		String formatDate = null;
		if (date != null) {
			SimpleDateFormat formater = new SimpleDateFormat(DATETIME_FORMAT);
			formatDate = formater.format(date);
		}
		return formatDate;
	}

	/**
	 * 根据指定的格式返回Date类型的时间 method: DateUtil getDate.
	 * 
	 * @param dateString
	 *            the date string
	 */
	public static Date getDate(String dateString, String format) {
		String newFormat = format;
		if (format == null || "".equals(format.trim())) {
			newFormat = DATETIME_FORMAT;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(newFormat);

		try {
			return formatter.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	public static int getIntDate(Date date) {
		String newFormat = TIME_FORMAT;
		SimpleDateFormat formatter = new SimpleDateFormat(newFormat);
		try {
			String s = formatter.format(date);
			int getIntDate = Integer.parseInt(s);
			return getIntDate;
		} catch (Exception e) {
			return 0;
		}
	}

	public static String getHour(Date date) {
		String newFormat = "HH";
		SimpleDateFormat formatter = new SimpleDateFormat(newFormat);
		try {
			String s = formatter.format(date);
			return s;
		} catch (Exception e) {
			return "02";
		}
	}

	public static boolean isCalcute(String hours) {
		boolean flag = false;
		String newFormat = "HH";
		SimpleDateFormat formatter = new SimpleDateFormat(newFormat);
		try {
			String hour = formatter.format(new Date());
			// 若为空则不计算全量
			if (null != hours) {
				String[] calString = hours.split(",");
				for (int i = 0; i < calString.length; i++) {
					if (calString[i].trim().equals(hour)) {
						flag = true;
						break;
					}
				}
			}

		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static boolean isCalcuteDefault(String days, int day) {
		boolean flag = false;
		try {
			String dayStr = String.valueOf(day);
			// 若为空则不计算全量
			if (null != days) {
				String[] calString = days.split(",");
				for (int i = 0; i < calString.length; i++) {
					if (calString[i].trim().equals(dayStr)) {
						flag = true;
						break;
					}
				}
			}

		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static String getDate(Date date) {
		String formatDate = null;
		if (date != null) {
			SimpleDateFormat formater = new SimpleDateFormat(DATE_FORMAT);
			formatDate = formater.format(date);
		}
		return formatDate;
	}

	public static String getTomorrowDate() {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		String dateString = formatter.format(date);
		return dateString;
	}

	public static long getDiffMinsByDate(String startTime) {
		SimpleDateFormat sd = new SimpleDateFormat(DATETIME_FORMAT);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long mins = 0l;
		try {
			diff = new Date().getTime() - sd.parse(startTime).getTime();
			long day = diff / nd;// 计算差多少天
			long hour = diff % nd / nh;// 计算差多少小时
			long min = diff % nd % nh / nm;// 计算差多少分钟
			// long sec = diff%nd%nh%nm/ns;//计算差多少秒
			mins = day * 24 * 60 + hour * 60 + min;

		} catch (ParseException e) {
			mins = 0l;
		}
		return mins;
	}

	/**
	 * 日期转换
	 */
	public static String getDateFormat(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat(DATETIME_FORMAT);
		return sf.format(date);
	}

	/**
	 * String转java.sql.date
	 * 
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp string2Date(String dateString) throws ParseException {
		Timestamp ts = Timestamp.valueOf(dateString);
		return ts;
	}

	/**
	 * 获取当月第一天
	 * 
	 * @return
	 */
	public static String getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat simpleFormate = new SimpleDateFormat(EDW_NOTIFY_DATE_FORMAT);
		return simpleFormate.format(calendar.getTime());
	}

	/**
	 * 获取上月第一天
	 * 
	 * @return
	 */
	public static String getLastMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat simpleFormate = new SimpleDateFormat(EDW_NOTIFY_DATE_FORMAT);
		return simpleFormate.format(calendar.getTime());
	}

}
