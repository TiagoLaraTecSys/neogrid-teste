package com.laratecsys.neogrid.utils;

public class ExtractMinute {

	public static Integer minute(String stringToExtract) {

		Integer minute = 0;
		String extractFieldTime = stringToExtract.substring(stringToExtract.length() - 5);

		if (extractFieldTime.contains("nance")) {
			return 5;
		} else {
			return Integer.parseInt(extractFieldTime.substring(0, 2));
		}

	}
}
