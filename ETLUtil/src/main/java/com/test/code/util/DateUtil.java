package com.test.code.util;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
	public static java.sql.Date getSQLData(java.util.Date utilDate){
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    return sqlDate;
	}
	public static Date getSomeDateWithTimZone(final String str, final TimeZone tz, final String inputFormat)
		    throws ParseException {
		  final SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
		  sdf.setTimeZone(tz);
		  return sdf.parse(str);
		}
	public static Date getSomeDate(final String str, final String inputFormat)
		    throws ParseException {
		  final SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
		  return sdf.parse(str);
		}
	public static String milliToDate(long millisecond){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		//Converting milliseconds to Date using Calendar
	       Calendar cal = Calendar.getInstance();
	       cal.setTimeInMillis(millisecond);
	       return df.format(cal.getTime());
	}
	public static void main(String ...strings) throws ParseException{
		//Date localTime=DateUtil.getSomeDateWithTimZone("20151101 200912", TimeZone.getDefault(),"yyyyMMdd HHmmss");
		//DateUtil.getSomeDateString(localTime,TimeZone.getTimeZone("GMT"),"yyyy-MM-dd HH:mm:ss");
		//DateUtil.getWebMethodsDateString(DateUtil.getSomeDate("20150722 110912", "yyyyMMdd HHmmss"),"{0, date, yyyy-MM-dd}T{0, date, HH:mm:ss}");
		System.out.println(getSQLData(getSomeDate("12/01/2015", "MM/dd/yyyy")));
	}	
}
