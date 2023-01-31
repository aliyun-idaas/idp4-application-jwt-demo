package com.idsmanager.demo.jwt.commons.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class DateUtils {

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";


    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String FULL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static final String TIME_FORMAT = "HH:mm";
    /**
     * Default month format
     */
    public static final String MONTH_FORMAT = "yyyy-MM";
    /**
     * Default day format
     */
    public static final String DAY_FORMAT = "dd";

    /**
     * Default date format
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private DateUtils() {
    }

    public static Date now() {
        return new Date();
    }


    //Create new  SimpleDateFormat
    private static SimpleDateFormat newDateFormat(String pattern) {
        return new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE);
    }

    public static String toDateTime(Date date) {
        return toDateText(date, DEFAULT_DATE_TIME_FORMAT);
    }

    public static String toDateText(Date date) {
        return toDateText(date, DATE_FORMAT);
    }

    public static String toDateText(Date date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        SimpleDateFormat dateFormat = newDateFormat(pattern);
        return dateFormat.format(date);
    }


    public static Date getDate(String dateText) {
        return getDate(dateText, DATE_FORMAT);
    }


    public static Date getDate(String dateText, String pattern) {
        DateFormat dateFormat = createDateFormat(pattern);
        try {
            return dateFormat.parse(dateText);
        } catch (ParseException e) {
            throw new IllegalStateException("Parse date from [" + dateText + "," + pattern + "] failed", e);
        }
    }

    private static DateFormat createDateFormat(String pattern) {
        return new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE);
    }


    public static boolean isToday(Date date) {
        if (date == null) {
            return false;
        }
        String dateAsText = toDateText(date);
        String todayAsText = toDateText(now());
        return dateAsText.equals(todayAsText);
    }

}
