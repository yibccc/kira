package com.zpq.utils;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class DateUtils {


    // 时间相加函数
    public static Time timeAdd(Time time1,Time time2){
        // 相加后的秒
        int second = time1.getSeconds() + time2.getSeconds();
        int minute = time1.getMinutes() + time2.getMinutes();
        int hour = time1.getHours() + time2.getHours();
        return new Time(hour,minute,second);
    }

    // 获取指定日期的星期数
    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

    // 将string类型的日期转换为Date
    public static Date strToDate(String dateStr) {
        if(dateStr == null){
            return null;
        }
        String[] split = dateStr.split("-");
        int year = Integer.parseInt(split[0]) + 100;
        int month = Integer.parseInt(split[1]) - 1;
        int day = Integer.parseInt(split[2]);
        Date date = new Date(year, month, day);
        return date;
    }

    // 将string类型时间转为Time
    public static Time strToTime(String timeStr) {
        if(timeStr == null){
            return null;
        }
        String[] split = timeStr.split(":");
        return new Time(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 0);
    }
}
