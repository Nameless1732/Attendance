package com.jie.attendance.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    public static String getFormatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date strToDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        ParsePosition parsePosition = new ParsePosition(0);
        return format.parse(strDate, parsePosition);
    }
}
