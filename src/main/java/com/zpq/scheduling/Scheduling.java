package com.zpq.scheduling;

import com.zpq.service.StoreService;
import com.zpq.service.impl.StoreServiceImpl;
import com.zpq.utils.DateUtils;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 根据Calculate处理后的数据计算排班规则
public class Scheduling {
    StoreService storeService = new StoreServiceImpl();
    // key: 记录对应天数   value：{key:半个小时为周期的 结束时间，value：剩余需要的人数}
    public static Map<Integer, Map<String, Integer>> sailingMap = new HashMap<>();


    // 计算第n天的排班表       excel表从周一开始
    public void getDayScheduling(int day, int storeId) {
        int week = day % 7 == 0 ? 7 : day % 7 + 1;
        int monday = day - week + 1;
        for (int i = monday; i <= week; i++) {        // 先将本周前面几天的数据加载出来  以便判断本周剩余可工作时长
            // key:半个小时为周期的结束时间，value：剩余需要的人数
            Map<String, Integer> sailingNum = new HashMap<>();
            getDaySailingMap(i, storeId, sailingNum);
            getPrepTimeScheduling(storeId, i, sailingNum);
            int excess = 0;
            Set<Map.Entry<String, Integer>> entries = sailingNum.entrySet();
            for (Map.Entry<String, Integer> entry : entries) {
                excess += entry.getValue();
            }
            System.out.println(excess);
        }

    }

    // 对开店前准备进行排班      #每天的排班都从这里开始
    public void getPrepTimeScheduling(int storeId, int day, Map<String, Integer> sailingNum) {
        // 获取该店的开店准备时间和闭店清理时间
        Time prepTime = storeService.getPrepTime(storeId);
        Time closureTime = storeService.getClosureTime(storeId);
        // 此处设置准备时间的周期为半小时     分钟大于0都当作半小时
        // 添加班次
        ManageScheduling.addScheduling(day, storeId, getStartTime(day, prepTime, storeId, sailingNum), getEndTime(day, closureTime, storeId, sailingNum));
    }

    // 加上准备时间后的开始时间
    public Time getStartTime(int day, Time preTime, int storeId, Map<String, Integer> sailingNum) {
        Time startTime;
        Time endTime = new Time(0, 0, 0);
        // 获取准备时间的人数
        int prepNum = Calculate.getPrepNum(storeId);
        int hours = preTime.getHours();
        int minutes = preTime.getMinutes();
        int seconds = preTime.getSeconds();
        if (day % 7 == 0 || day % 7 == 6) { // 周末
            startTime = new Time(10 - hours, -minutes, -seconds);
            endTime.setHours(10);
        } else { // 工作日
            startTime = new Time(9 - hours, -minutes, -seconds);
            endTime.setHours(9);
        }
        // 将准备时间的排班存到map集合
        addSailingMap(startTime, endTime, prepNum, sailingNum);
        return startTime;
    }

    public Time getEndTime(int day, Time cleanTime, int storeId, Map<String, Integer> sailingNum) {
        Time startTime;
        Time endTime;
        int closureNum = Calculate.getClosureNum(storeId);
        if (day % 7 == 0 || day % 7 == 6) { // 周末
            startTime = new Time(22, 0, 0);
            // 将准备时间的排班存到map集合
        } else { // 工作日
            startTime = new Time(21, 0, 0);
        }
        endTime = DateUtils.timeAdd(startTime, cleanTime);
        addSailingMap(startTime, endTime, closureNum, sailingNum);
        return endTime;
    }

    // 计算day的sailingMap
    public static void getDaySailingMap(int day, int storeId, Map<String, Integer> sailingNum) {

        // 用于相加计算
        Time addTime = new Time(0, 30, 0);
        List numHalfHour = Calculate.getNumHalfHour(storeId, day);
        Time time = (Time) numHalfHour.get(0);
        for (int i = 1; i < numHalfHour.size(); i++) {
            time = DateUtils.timeAdd(addTime, time);
            sailingNum.put(String.valueOf(time), (Integer) numHalfHour.get(i));
        }
        sailingMap.put(day, sailingNum);
        System.out.println(sailingMap.get(day));
    }

    // 将对应时间按半小时为周期存到sailingMap中
    public void addSailingMap(Time startTime, Time endTime, int nums, Map<String, Integer> sailingNum) {
        Time addTime = new Time(0, 30, 0);
        while (startTime.compareTo(endTime) < 0) {
            startTime = DateUtils.timeAdd(addTime, startTime);
            sailingNum.put(String.valueOf(startTime), nums);
        }
    }
}