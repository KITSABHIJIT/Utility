package com.build.util;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

	public static String getHrMinSec(long timeInMilliSeconds){
		if(timeInMilliSeconds<0){
			return timeInMilliSeconds+"";
		}
		long seconds = timeInMilliSeconds / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long days = hours / 24;
		String time = days + " days " + hours % 24 + " hr " + minutes % 60 + " min " + seconds % 60+ " sec "+ timeInMilliSeconds % 1000+ " millisec";
		return time;
	}
	public static String getWebMethodsDateString(final Date dateTime,final String outputFormat){
		MessageFormat mf = new MessageFormat(outputFormat);

		System.out.println("local time : " + dateTime);
		Object [] args = {dateTime};
		String result=mf.format(args);
		result=result.replaceAll("\\p{Z}","");
		System.out.println("time in GMT : " + result);
		return result;
	}

	public static String getSomeDateString(final Date dateTime,final TimeZone tz,final String outputFormat){
		DateFormat converter = new SimpleDateFormat(outputFormat);
		converter.setTimeZone(tz);
		System.out.println("local time : " + dateTime);
		String result=converter.format(dateTime);
		System.out.println("time in GMT : " + result);
		return result;
	} 

	public static String getDateToString(final Date dateTime,final String outputFormat){
		DateFormat converter = new SimpleDateFormat(outputFormat);
		String result=converter.format(dateTime);
		return result;
	} 
	public static java.sql.Date getSQLDate(java.util.Date utilDate){
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
	public static java.util.Date getUtilDate(java.sql.Date sqlDate){
		java.util.Date utilDate = new java.sql.Date(sqlDate.getTime());
		return utilDate;
	}

	public static Date getSomeDateWithTimZone(final String str, final TimeZone tz, final String inputFormat)
			throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
		sdf.setTimeZone(tz);
		return sdf.parse(str);
	}
	public static Date getSomeDate(final String str, final String inputFormat)
			throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat(inputFormat,Locale.ENGLISH);
		if(StringUtil.isBlankOrEmpty(str)) {
			return sdf.parse(getDateToString(new Date(),inputFormat));
		}else {
			return sdf.parse(str.trim());
		}
		
	}
	public static String milliToDate(long millisecond){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		//Converting milliseconds to Date using Calendar
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millisecond);
		return df.format(cal.getTime());
	}

	public static List<Date> getDatesFromDateRange(Date startDate, Date endDate, String manner){
		List<Date> dateList = new ArrayList<Date>();
		Calendar beginCalendar  = Calendar.getInstance();
		beginCalendar .setTime(startDate);
		Calendar finishCalendar  = Calendar.getInstance();
		finishCalendar .setTime(endDate);
		if("MONTHLY".equalsIgnoreCase(manner)){
			dateList.add(beginCalendar.getTime());
			while (beginCalendar.before(finishCalendar)) {
				beginCalendar.add(Calendar.MONTH, 1);
				beginCalendar.set(Calendar.DAY_OF_MONTH, 1);
				beginCalendar.add(Calendar.DATE, -1);  
				if(beginCalendar.before(finishCalendar)){
					dateList.add(beginCalendar.getTime());
				}
				beginCalendar.add(Calendar.DATE, 1); 
				if(beginCalendar.before(finishCalendar)){
					dateList.add(beginCalendar.getTime());
				}
			}
			dateList.add(finishCalendar.getTime());
		}else if("WEEKLY".equalsIgnoreCase(manner)){
			dateList.add(beginCalendar.getTime());
			while (beginCalendar.before(finishCalendar)) {
				beginCalendar.add(Calendar.WEEK_OF_MONTH, 1);
				beginCalendar.set(Calendar.DAY_OF_WEEK, 1);
				beginCalendar.add(Calendar.DATE, -1);  
				if(beginCalendar.before(finishCalendar)){
					dateList.add(beginCalendar.getTime());
				}
				beginCalendar.add(Calendar.DATE, 1); 
				if(beginCalendar.before(finishCalendar)){
					dateList.add(beginCalendar.getTime());
				}
			}
			dateList.add(finishCalendar.getTime());
		}else if("YEARLY".equalsIgnoreCase(manner)){
			dateList.add(beginCalendar.getTime());
			while (beginCalendar.before(finishCalendar)) {
				beginCalendar.add(Calendar.YEAR, 1);
				beginCalendar.set(Calendar.DAY_OF_YEAR, 1);
				beginCalendar.add(Calendar.DATE, -1);  
				if(beginCalendar.before(finishCalendar)){
					dateList.add(beginCalendar.getTime());
				}
				beginCalendar.add(Calendar.DATE, 1); 
				if(beginCalendar.before(finishCalendar)){
					dateList.add(beginCalendar.getTime());
				}
			}
			dateList.add(finishCalendar.getTime());
		}
		
		return dateList;
	}
	
	public static Date getFirstDayOfTheMonth(Date input){
		Calendar calendar  = Calendar.getInstance();
		calendar .setTime(input);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	public static Date getFirstDayOfTheYear(Date input){
		Calendar calendar  = Calendar.getInstance();
		calendar .setTime(input);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}
	
	public static Date getLastDayOfTheMonth(Date input){
		Calendar calendar  = Calendar.getInstance();
		calendar .setTime(input);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1); 
		return calendar.getTime();
	}
	
	public static Date getLastDayOfTheYear(Date input){
		Calendar calendar  = Calendar.getInstance();
		calendar .setTime(input);
		calendar.add(Calendar.YEAR, 1);
		calendar.add(Calendar.DATE, -1); 
		return calendar.getTime();
	}
	
	public static int getYear(Date date) {
		Calendar calendar  = Calendar.getInstance();
		calendar .setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	public static void main(String ...strings) throws ParseException{
		//Date localTime=DateUtil.getSomeDateWithTimZone("20151101 200912", TimeZone.getDefault(),"yyyyMMdd HHmmss");
		//DateUtil.getSomeDateString(localTime,TimeZone.getTimeZone("GMT"),"yyyy-MM-dd HH:mm:ss");
		//DateUtil.getWebMethodsDateString(DateUtil.getSomeDate("20150722 110912", "yyyyMMdd HHmmss"),"{0, date, yyyy-MM-dd}T{0, date, HH:mm:ss}");
		//System.out.println(getSQLDate(getSomeDate("12/01/2015", "MM/dd/yyyy")));
		//System.out.println(getDatesFromDateRange(getSomeDate("20150112", "yyyyMMdd"),getSomeDate("20170417", "yyyyMMdd"),"YEARLY"));
		System.out.println(DateUtil.getSomeDate("August 25, 2015 03:38 AM", "MMMMM dd, yyyy hh:mm a"));
		System.out.println(DateUtil.getSomeDate("August 25, 2015 03:38 AM", "MMMMM dd, yyyy hh:mm a"));
		
	}	



}
