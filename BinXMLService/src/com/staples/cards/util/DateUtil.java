package com.staples.cards.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	public static String getDateString(String dateFormat){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}
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

}
