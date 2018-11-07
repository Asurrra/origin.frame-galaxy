package com.cyw.origin.frame.galaxy.util;

import jodd.datetime.JDateTime;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Calendar;
import java.util.Date;

public abstract class DateUtil {

    private static Date maxDate;

    private static Date minDate;

    private static final String MONTH_PATTERN = "yyyy-MM";
    public static final FastDateFormat SDF_MONTH_PATTERN = FastDateFormat.getInstance(MONTH_PATTERN);

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final FastDateFormat SDF_DATE_PATTERN = FastDateFormat.getInstance(DATE_PATTERN);

    private static final String DATETIME_PATTERN_NO_SECOND = "yyyy-MM-dd HH:mm";
    public static final FastDateFormat SDF_DATETIME_PATTERN_NO_SECOND = FastDateFormat
            .getInstance(DATETIME_PATTERN_NO_SECOND);

    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final FastDateFormat SDF_DATETIME_PATTERN = FastDateFormat.getInstance(DATETIME_PATTERN);

    private static final String DATETIME_PATTERN_WITH_DOT_FULL = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final FastDateFormat SDF_DATETIME_PATTERN_WITH_DOT_FULL = FastDateFormat
            .getInstance(DATETIME_PATTERN_WITH_DOT_FULL);

    private static final String DATETIME_T_PATTERN_NO_SECOND = "yyyy-MM-dd'T'HH:mm";
    public static final FastDateFormat SDF_T_DATETIME_PATTERN_NO_SECOND = FastDateFormat
            .getInstance(DATETIME_T_PATTERN_NO_SECOND);

    private static final String DATETIME_T_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    public static final FastDateFormat SDF_T_DATETIME_PATTERN = FastDateFormat.getInstance(DATETIME_T_PATTERN);

    private static final String DATETIME_T_PATTERN_WITH_DOT_FULL = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final FastDateFormat SDF_T_DATETIME_PATTERN_WITH_DOT_FULL = FastDateFormat
            .getInstance(DATETIME_T_PATTERN_WITH_DOT_FULL);

    private static final String DATETIME_PATTERN_WITH_COMPRESS = "yyyyMMddHHmmss";
    public static final FastDateFormat SDF_DATETIME_PATTERN_COMPRESS = FastDateFormat
            .getInstance(DATETIME_PATTERN_WITH_COMPRESS);

    private static final String DATETIME_PATTERN_DATE_WITH_COMPRESS = "yyyyMMdd";
    public static final FastDateFormat SDF_DATETIME_PATTERN_DATE_COMPRESS = FastDateFormat
            .getInstance(DATETIME_PATTERN_DATE_WITH_COMPRESS);

    private static final String DATETIME_PATTERN_TIME_WITH_COMPRESS = "HHmm";
    public static final FastDateFormat SDF_DATETIME_PATTERN_TIME_COMPRESS = FastDateFormat
            .getInstance(DATETIME_PATTERN_TIME_WITH_COMPRESS);

    /**
     * 当天起始时间
     *
     * @param oDate
     * @return
     */
    public static Date getDayStart(Date oDate) {
        JDateTime jdt = new JDateTime(oDate);
        jdt.setTime(0, 0, 0, 0);
        return jdt.convertToDate();
    }

    /**
     * 当天结束时间
     *
     * @param oDate
     * @return
     */
    public static Date getDayEnd(Date oDate) {
        JDateTime jdt = new JDateTime(oDate);
        jdt.setTime(23, 59, 59, 999);
        return jdt.convertToDate();
    }

    /**
     * 当天起始时间
     *
     * @param oDate
     * @return
     */
    public static Date getMonthStart(Date oDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(oDate);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return getDayStart(c.getTime());
    }

    /**
     * 当天结束时间
     *
     * @param oDate
     * @return
     */
    public static Date getMonthEnd(Date oDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(oDate);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getDayEnd(c.getTime());
    }

    /**
     * 获取星期几
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取星期几
     *
     * @param day
     * @return
     */
    public static String getDayOfWeek(int day) {

        switch (day) {
            case 0:
                return "SUN";
            case 1:
                return "MON";
            case 2:
                return "TUE";
            case 3:
                return "WED";
            case 4:
                return "THU";
            case 5:
                return "FRI";
            case 6:
                return "SAT";
            default:
                return "";
        }
    }

    public static Date addSecond(Date date, Integer second) {
        JDateTime jDateTime = new JDateTime(date);
        jDateTime.addSecond(second);
        return jDateTime.convertToDate();
    }

    public static Date addHours(Date date, Integer hour) {
        JDateTime jDateTime = new JDateTime(date);
        jDateTime.addHour(hour);
        return jDateTime.convertToDate();
    }

    public static Date addDay(Date date, Integer day) {
        JDateTime jDateTime = new JDateTime(date);
        jDateTime.addDay(day);
        return jDateTime.convertToDate();
    }

    public static Date addMonth(Date date, Integer month) {
        JDateTime jDateTime = new JDateTime(date);
        jDateTime.addMonth(month);
        return jDateTime.convertToDate();
    }

    public static Date addYear(Date date, Integer year) {
        JDateTime jDateTime = new JDateTime(date);
        jDateTime.addYear(year);
        return jDateTime.convertToDate();
    }

    public static final String dateDiff(Date date1, Date date2) {
        Long diff = Math.abs(date1.getTime() - date2.getTime());
        Long day = diff / (24 * 60 * 60 * 1000);
        Long hour = (diff / (60 * 60 * 1000) - day * 24);
        Long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        Long sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        StringBuffer diffStr = new StringBuffer();
        Boolean show = false;
        if (day > 0) {
            diffStr.append(day);
            diffStr.append("天");
            show = true;
        }
        if (show || hour > 0) {
            diffStr.append(hour);
            diffStr.append("小时");
            show = true;
        }
        if (show || min > 0) {
            diffStr.append(min);
            diffStr.append("分");
        }
        diffStr.append(sec);
        diffStr.append("秒");
        return diffStr.toString();
    }
}
