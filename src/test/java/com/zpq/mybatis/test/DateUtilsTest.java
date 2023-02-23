package com.zpq.mybatis.test;

import com.zpq.scheduling.ExcelToList;
import com.zpq.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.util.List;


public class DateUtilsTest {

    @Test
    public void testGetWeak(){
        Date date = new Date(33, 2, 10);    // 1900 年开始计时
        int weekOfDate = DateUtils.getWeekOfDate(date);
        System.out.println(weekOfDate); // 5
    }


    @Test
    public void testReadExcel(){
        List list = ExcelToList.getDayPredication(1, 1);
        System.out.println(list);
    }

    @Test
    public void testTime(){
        Time time = new Time(1, -1, 1);
        System.out.println(time.getMinutes());
        System.out.println(time.getHours());
    }
}
