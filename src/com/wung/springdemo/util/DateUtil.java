package com.wung.springdemo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	public static final String dateFormat_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String dateFormat_yyyy_mm_dd_hh_mm_ss = "yyyy-MM-dd HH:mm:ss";
	
	public static String formatDate(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	
	public static String formatDate(String date,String format){
		if(date == null){
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.format(sdf.parse(date));
		} catch (ParseException e) {
			
			e.printStackTrace();
			return null;
		}

	}
	
	public static Date parseDate(String date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date addDate(Date date, int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);//加天
		return calendar.getTime();
	}

	public static String addDateString(Date date, int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);//加天
		return DateUtil.formatDate(calendar.getTime(), dateFormat_YYYY_MM_DD);
	}
	
	/**
	 * 获取当前系统时间
	 * @return timeNow
	 */
	public static String getNowTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		Calendar cal = Calendar.getInstance();
        String timeNow= sdf.format(cal.getTime());
		return timeNow;
	}
	
	/**
	 * 北京时间转为格林威治时间
	 * @param date
	 * @return
	 */
	public static String beiJingToGMT(String date,String type){
		Date d=DateUtil.parseDate(date, type);
		Long gmt=d.getTime()-TimeZone.getTimeZone("GMT+8").getOffset(0);
		return DateUtil.formatDate(new Date(gmt), type);
	}

	/**
	 * 格林威治时间转北京
	 * @param date
	 * @return
	 */
	public static String GMTtoBeiJing(String date,String type){
		Date d=DateUtil.parseDate(date, type);
		Long gmt=d.getTime()+TimeZone.getTimeZone("GMT+8").getOffset(0);
		return DateUtil.formatDate(new Date(gmt), type);
	}
	
	/**
	 * 计算两个日期相差几天
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static int timeDifByDay(String time1,String time2){
		Date d1=DateUtil.parseDate(time1, dateFormat_YYYY_MM_DD);
		long l1=d1.getTime();
		Date d2=DateUtil.parseDate(time2, dateFormat_YYYY_MM_DD);
		long l2=d2.getTime();
		long r=l1-l2;
		long s=r/1000/60/60/24;
		return (int)s;
	}
	
	/**
	 *获取当前月份的前3天
	 *@author King
	 */
	public static String firstThreeDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH)+2); 
        String lastDay = format.format(ca.getTime());
        return lastDay;
	}
	
	/**
	 * 获取当前系统日期
	 * @return nowDate
	 */
	public static String getNowDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Calendar cal = Calendar.getInstance();
        String nowDate= sdf.format(cal.getTime());
		return nowDate;
	}
	
	/**
	 * 获取当月第一天
	 * @return nowDate
	 */
	public static String getFirstDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH)); 
        String firstDay = format.format(ca.getTime());
        return firstDay;
	}

	/**
	 * 获取下个月第一天
	 * @return nowDate
	 */
	public static String getLastDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
        String lastDate = format.format(ca.getTime());
        return lastDate;
	}
	
	public static void main(String[] args) {
//		DateUtil d  = new DateUtil();
//		System.out.println(d.formatDate("2015-1-11", DateUtil.dateFormat_YYYY_MM_DD));
	}
	
}
