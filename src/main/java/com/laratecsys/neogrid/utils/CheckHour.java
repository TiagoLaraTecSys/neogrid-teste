package com.laratecsys.neogrid.utils;

import java.util.Date;

public class CheckHour {

	public static boolean isLaunchTime(Date time) {
		@SuppressWarnings("deprecation")
		Integer hour = time.getHours();
		Integer minutes = time.getMinutes();
		return hour == 12 && minutes >00  ? true : false;

	}

	public static boolean isLaborTime(Date time) {
		@SuppressWarnings("deprecation")
		Integer hour = time.getHours();
		return hour == 16 ? true : false;

	}

}
