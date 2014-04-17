package com.fuwei.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringTODate {
	public static Date changeStringTODate(String stringDate) {
		String formatPattern = "yyyy/MM/dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatPattern);
		Date date = null;
		try {
			date = simpleDateFormat.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static boolean canChangeStringTODate(String stringDate) {
		try {
			String formatPattern = "yyyy/MM/dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					formatPattern);

			Date date = simpleDateFormat.parse(stringDate);
			if (date != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}
}
