package com.fq.slendit.user.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {

	public static LocalDate getCurrentDate() {
		return LocalDate.now();
	}

	public static LocalDateTime getCurrentDateTime() {
		return LocalDateTime.now();
	}
}
