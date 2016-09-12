package com._520it.ssh.utils;

import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
public class DateUtil {
	
	public static Date endTimeOfDay(Date d) {
		return DateUtils.addSeconds(
				DateUtils.addDays(DateUtils.truncate(d, Calendar.DATE), 1), -1);
	}
	public static Date beginTimeOfDay(Date d) {
		return DateUtils.addSeconds(
				DateUtils.addDays(DateUtils.truncate(d, Calendar.DATE),0), 0);
	}
	
	
	public static void main(String[] args) {
		System.out.println(beginTimeOfDay(new Date()));
		System.out.println(endTimeOfDay(new Date()));
	}
}
