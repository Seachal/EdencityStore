package com.edencity.store.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateFormatUtils {

    public static final String FORMAT_1 = "MM月dd日 E";
    public static final String FORMAT_2 = "yyyyMMdd";
    public static final String FORMAT_3 = "yyyyMMddHHMMss" ;
    public static final String FORMAT_4 = "yyyy-MM-dd";
    public static final String FORMAT_5 = "yyyy年MM月dd日 HH:MM";
    public static final String FORMAT_7 = "yyyy-MM-dd HH:MM";
    public static final String FORMAT_6 = "yyyy-MM-dd HH:MM:ss";


    public static String getFormattedDateString(String value, String format){

        Date date = getDateFromString(value);
        if(date != null){
           return new SimpleDateFormat(format, Locale.CHINA).format(date);
        }
        return null;
    }


    public static Date getDateFromString(String value){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_2, Locale.CHINA);
        try {
           return simpleDateFormat.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;

    }
    /**
     * 获取系统时间戳
     * @return
     */
    public long getCurTimeLong(){
        long time=System.currentTimeMillis();
        return time;
    }
    /**
     * 获取当前时间
     * @param pattern
     * @return
     */
    public static String getCurDate(String pattern){
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new java.util.Date());
    }

    /**
     * 时间戳转换成字符窜
     * @param milSecond
     * @param pattern
     * @return
     */
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 将字符串转为时间戳
     * @param dateString
     * @param pattern
     * @return
     */
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try{
            date = dateFormat.parse(dateString);
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }
}
