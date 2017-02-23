package com.upa.pet_shop.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * 为一个Date对象加上指定的天数
	 */
	public static Date addDay(Date date, int dayNum){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, dayNum);
		return calendar.getTime();
	}
	
	/**
	 * date2 - date1
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int hoursBetweenDate(Date date1, Date date2){
		int hours = (int)(date2.getTime() - date1.getTime())/(1000*3600);
		return hours;
	}
	
	/**
	 * 获取时间戳
	 */
	public static String getTimestamp(){
		return Long.toString(System.currentTimeMillis());
	}
	
	/**
	 * 获取当前时间字符串
	 */
	public static String getCurrentTimeString(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
	
	/**
	 * 转换正标准时间格式
	 */
	public static String toNormalString(Date date){
		if(date==null){
			return "";
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 转为年-月-日格式字符串
	 */
	public static String toYearMonthDayString(Date date){
		if(date==null){
			return "";
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/**
	 * 转为年-月格式字符串
	 */
	public static String toYearMonthString(Date date){
		if(date==null){
			return "";
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		return sdf.format(date);
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss转Date
	 * 支持：
	 * yyyy-MM-dd
	 * yyyy-MM-dd HH:mm
	 * yyyy-MM-dd HH:mm:ss
	 * @param timeText
	 * @return
	 * @throws Exception 
	 */
	public static Date stringToDate(String timeText) throws Exception{
		timeText = timeText.trim();
		int strLength = timeText.length();
		String dataFormat = "";
		if(strLength==10){//yyyy-MM-dd
			dataFormat = "yyyy-MM-dd";
		}else if(strLength==16){//yyyy-MM-dd HH:mm
			dataFormat = "yyyy-MM-dd HH:mm";
		}else if(strLength==19){//yyyy-MM-dd HH:mm:ss
			dataFormat = "yyyy-MM-dd HH:mm:ss";
		}else{
			throw new Exception("invalid time format: "+timeText);
		}
		try{
			SimpleDateFormat sdfDateFormat = new SimpleDateFormat(dataFormat);
			Date date = sdfDateFormat.parse(timeText);
			return date;
		}catch(Exception e){
			throw new Exception("invalid time format: "+timeText);
		}
	}
}
