package com.fq.slendit.user.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getCurrentDate() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
		return simpleDateFormat.format(new Date());
	}

	public static String getCurrentDateTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
		return simpleDateFormat.format(new Date());
	}
}
