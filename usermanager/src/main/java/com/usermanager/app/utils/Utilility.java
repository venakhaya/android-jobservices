package com.usermanager.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

public class Utilility {
	public static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

	public static int hoursDifference(String date1) {

		final int MILLI_TO_HOUR = 1000 * 60 * 60;
		int diff = 0;
		SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
		try {
			diff = (int) (fmt.parse(date1).getTime() - new Date().getTime()) / MILLI_TO_HOUR;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diff;
	}

	public static boolean isValidFormat(String value) {
		LocalDateTime ldt = null;
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH);

		try {
			ldt = LocalDateTime.parse(value, fomatter);
			String result = ldt.format(fomatter);
			return result.equals(value);
		} catch (DateTimeParseException e) {
			try {
				LocalDate ld = LocalDate.parse(value, fomatter);
				String result = ld.format(fomatter);
				return result.equals(value);
			} catch (DateTimeParseException exp) {
				try {
					LocalTime lt = LocalTime.parse(value, fomatter);
					String result = lt.format(fomatter);
					return result.equals(value);
				} catch (DateTimeParseException e2) {
					e2.printStackTrace();
				}
			}
		}

		return false;
	}
}
