package com.zpq.scheduling;

import com.zpq.mapper.EmployeeMapper;
import com.zpq.mapper.SailingMapper;
import com.zpq.pojo.Employee;
import com.zpq.pojo.Sailing;
import com.zpq.service.EmployeeService;
import com.zpq.service.impl.EmployeeServiceImpl;
import com.zpq.utils.DateUtils;
import com.zpq.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.util.*;

// 固定排班规则
public class ManageScheduling {
    // 每周最大工作时长
    public static final int MAX_WEEK_WORK_HOUR = 40;
    // 每天最大工作时长
    public static final int MAX_DAY_WORK_TIME = 8;
    // 班次的最短连续工作时长
    public static final int MIN_SAILING_WORK_TIME = 2;
    // 班次的最长连续工作时长
    public static final int MAX_SAILING_WORK_TIME = 4;

    static SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    static SailingMapper sailingMapper = sqlSession.getMapper(SailingMapper.class);


    // 存放员工当月及每天参与排班的时间
    // id:存放天数     value:{id:员工id value:工作时长 半个小时记一次}
    static Map<Integer, Map<Integer, Integer>> employeeScheduling = new HashMap<>();
    private static EmployeeService employeeService = new EmployeeServiceImpl();

    // 排班集合
    static List<Sailing> sailings = new ArrayList<>();

    // 在addScheduling修改
    private static Integer store;

    static {     // 初始化员工的排班时间
        for (int i = 1; i <= 30; i++) {  // 展示一个月的数据
            HashMap<Integer, Integer> employeeMap = new HashMap<>();
            for (int j = 1; j <= 80; j++) { // 员工id
                employeeMap.put(j, 0);   //  初始化工作时间为0
            }
            employeeScheduling.put(i, employeeMap);
        }
    }

    /**
     * 添加班次的入口
     * @param day       日期
     * @param storeId   店铺id
     * @param startTime 开始时间
     * @param endTime   对应天数的结束时间
     */
    public static void addScheduling(int day, int storeId, Time startTime, Time endTime) {
        sailings.clear();   // 情况list集合
        store = storeId;
        // 先对有偏好的员工进行排班
        List<Employee> employeeCheckWorkday = employeeService.getEmployeeCheckWorkday(store, day % 7);
        checkWorkTime(employeeCheckWorkday, startTime,endTime, day);
        // 对剩下的没有偏好的员工进行排班
        List<Employee> employeeNotCheck = employeeService.getEmployeeNotCheck(store);
        Random random = new Random();
        Time addTime = new Time(0, 30, 0);
        Time time = DateUtils.timeAdd(addTime, startTime);
        while (time.compareTo(endTime) <= 0) {
            // 检查对应时间的需求人数是否需要排班
            if (Scheduling.sailingMap.get(day).get(String.valueOf(time)) <= 0) {    // 该时间需要的人数为0
                time = DateUtils.timeAdd(time, addTime); // 时间后移
                startTime = DateUtils.timeAdd(startTime, addTime);
            } else {
                // 随机选取员工
                Employee employee = employeeNotCheck.get(random.nextInt(employeeNotCheck.size()));
                // 将员工添加到该班次
                addNoCheckSailing(employee, startTime,endTime, day);
            }
        }
        for (int i = 0; i < sailings.size(); i++) {
            System.out.println(sailings.get(i));
        }
    }

    // 先对有星期和时间偏好的员工进行排班
    private static List<Sailing> checkWorkTime(List<Employee> employees, Time startTime, Time lastTime, int day) {
        List<Sailing> sailings = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            int jobId = employee.getJobId();
            Time[] preferenceTime = employeeService.getCheckTime(employee.getId());
            Integer time = checkTime(employee.getId(), day);    // 检查员工对应天数剩余的排班半小时数
            if (time < MIN_SAILING_WORK_TIME * 2) { // 可排的时间小于最小班次时间
                break;
            }
            if (preferenceTime[0] != null) { // 说明员工没有时间偏好
                Time preferenceStartTime = preferenceTime[0];   // 偏好的开始时间
                Time preferenceEndTime = preferenceTime[1];     // 偏好的结束时间
                time = Math.min(time, differenceHalf(preferenceStartTime, preferenceEndTime));    // 计算对应天数剩余的排班半小时数和偏好的半小时数的最小值作为排班的时间段
                setScheduling(employee.getId(), jobId,day, preferenceStartTime, lastTime,time);
            } else {    // 对没有时间 但有星期偏好的员工进行排班
                // 可排班的时间满足两小时
                setScheduling(employee.getId(), jobId,day, startTime, lastTime,time); // 设置对应的排班表并更新集合
            }
        }
        Time[] times = employeeService.getCheckTime(1);
        return sailings;
    }


    // 对没有时间偏好的员工进行排班
    public static boolean addNoCheckSailing(Employee employee, Time startTime, Time lastTime, int day) {
        Integer time = checkTime(employee.getId(), day);    // 该员工最少可排班的半小时数
        int jobId = employee.getJobId();
        if (time < MIN_SAILING_WORK_TIME * 2) {
            return false;
        }
        setScheduling(employee.getId(),jobId, day, startTime,lastTime,time);
        return true;
    }

    /**
     * 调用时传入开始时间，在该方法中进行排班计算，并更新map集合
     *
     * @param id        员工id
     * @param day       对应的天数
     * @param startTime 班次的开始时间
     * @param time      可排班的最大半小时数
     */
    private static void setScheduling(int id, int jobId,int day, Time startTime,Time lastTime ,Integer time) {
        time = Math.min(getNotConflictTimes(startTime,lastTime), time);//  排班的最小半小时数
        Time addTime = new Time(0, 30, 0);    // 用于时间相加
        Time endTime = DateUtils.timeAdd(addTime, startTime);
        for (int i = 0; i < time; i++) {
            Integer num = Scheduling.sailingMap.get(day).get(String.valueOf(endTime));// 通过半小时的结束时间查询该时间段最少需要的人数
            // 先排两个小时的班次  --满足最小排班班次
            if (i < 2 * MIN_SAILING_WORK_TIME) {
                // 更新employeeScheduling  --用于检查对应员工时间是否冲突
                employeeScheduling.get(day).put(id, employeeScheduling.get(day).get(id) + 1);   // 将员工对应天数的工作时长+1
                // 更新sailingMap  --用于记录对应时间段最少需要的人数
                Scheduling.sailingMap.get(day).put(String.valueOf(endTime), num - 1);    // 对应时间段最少需要的人数-1
                endTime = DateUtils.timeAdd(endTime, addTime);  // 结束时间后延
            } else {     // 满足最小排班班次后检查后面的是否需要继续排班

                while (num != null && num > 0 && i < time) {
                    // 更新employeeScheduling  --用于检查对应员工时间是否冲突
                    employeeScheduling.get(day).put(id, employeeScheduling.get(day).get(id) + 1);   // 将员工对应天数的工作时长+1
                    // 更新sailingMap  --用于记录对应时间段最少需要的人数
                    Scheduling.sailingMap.get(day).put(String.valueOf(endTime), num - 1);    // 对应时间段最少需要的人数-1
                    endTime = DateUtils.timeAdd(endTime, addTime);
                    num = Scheduling.sailingMap.get(day).get(String.valueOf(endTime));  // 时间后移后 获取新的时间段内需要的人数
                    i++;
                }
            }
        }
        endTime.setMinutes(endTime.getMinutes() - 30);// 将结束时间减半个小时
        if (time < MIN_SAILING_WORK_TIME * 2) { // 当前班次时间不足最小排班时间 设置为自由班次
                id = 0;
        }

        String date = "2023-05-"+(day < 10 ?"0"+day:day);
        Sailing sailing = new Sailing(id, store, jobId,date, String.valueOf(startTime), String.valueOf(endTime));
        sailingMapper.addSailing(sailing);
        sailings.add(sailing);
    }


    // 查看指定排班的时间是否冲突
    // 返回对应天数最大可排的半小时数
    public static Integer checkTime(int employeeId, int day) {
        int weekTime = 0;
        for (int i = day; (i - 1) % 7 >= 0; i--) {
            // 第i天的排班表
            Map<Integer, Integer> dayScheduling = employeeScheduling.get(i);
            // 员工id为employeeId的员工day天的工作时长
            Integer dayTime = dayScheduling.get(employeeId);

            weekTime += (dayTime == null ? 0 : dayTime);    // 将第i天的值班数据加到周值班时间
        }
        Integer time = employeeScheduling.get(day).get(employeeId);
        if (time == null) {
            time = 0;
        }
        return Math.min(MAX_WEEK_WORK_HOUR * 2 - weekTime, MAX_DAY_WORK_TIME * 2 - time);
    }

    // 计算两个时间之间相差的半小时数
    private static Integer differenceHalf(Time startTime, Time endTime) {
        return (endTime.getHours() - startTime.getHours()) * 2 + (endTime.getMinutes() - startTime.getMinutes());
    }

    /**
     * 午餐时间为    11：00 - 14：00
     * 晚餐时间为    17：00 - 20：00
     *
     * @param startTime 排班的开始时间
     * @param lastTime  每天的最后时间
     * @return 不覆盖用餐时间的最大可排半小时数
     */
    private static Integer getNotConflictTimes(Time startTime, Time lastTime) {
        int time = MAX_SAILING_WORK_TIME * 2;
        int last = 0;   // 记录该时间到整天结束的半小时数
        // 当开始值班时间在 10:00-11:00    16:00-17:00两个区间内时有可能整个排班时间覆盖用餐时间
        Time[] preLunchTime = {new Time(10, 0, 0), new Time(11, 0, 0)};
        Time[] preDinnerTime = {new Time(16, 0, 0), new Time(17, 0, 0)};
        Time addTime = new Time(0,30,0);
        // 当前排班开始时间有可能覆盖用餐时间
        if (startTime.compareTo(preLunchTime[0]) >= 0 && startTime.compareTo(preLunchTime[1]) <= 0) { // 有可能覆盖午餐时间
            while(preLunchTime[0].compareTo(startTime) <= 0){
                // 时间后移
                preLunchTime[0] = DateUtils.timeAdd(preLunchTime[0],addTime);
                // 可安排的半小时数减一
                time--;
            }

        } else if (startTime.compareTo(preDinnerTime[0]) >= 0 && startTime.compareTo(preDinnerTime[1]) <= 0) {  // 有可能覆盖晚餐时间
            while(preDinnerTime[0].compareTo(startTime) <= 0){
                // 时间后移
                preDinnerTime[0] = DateUtils.timeAdd(preDinnerTime[0],addTime);
                // 可安排的半小时数减一
                time--;
            }
        }
        while(startTime.compareTo(lastTime)<0 && last < 8){
            startTime = DateUtils.timeAdd(startTime,addTime);   // 时间后移
            last++; // 离整天结束的半小时数
        }
        return Math.min(last,time);
    }

}
