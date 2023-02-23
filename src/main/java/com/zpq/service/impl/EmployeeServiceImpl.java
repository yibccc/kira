package com.zpq.service.impl;

import com.zpq.mapper.EmployeeMapper;
import com.zpq.service.EmployeeService;
import com.zpq.pojo.Employee;
import com.zpq.utils.DateUtils;
import com.zpq.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
    // 将店铺和对应的所有员工存到mapper集合中
    Map<Integer, List<Employee>> allEmployeeMap = new HashMap();

    @Override
    public List<Employee> getEmployeeByStoreId(int storeId) {
        if (allEmployeeMap.get(storeId) == null) {
            allEmployeeMap.put(storeId,employeeMapper.getEmployeeByStoreId(storeId));
        }
        return allEmployeeMap.get(storeId);
    }

    @Override
    public List<Employee> getEmployeeNotCheck(int storeId) {
        List<Employee> allEmployee = getEmployeeByStoreId(storeId);
        List<Employee> noCheckEmployees = new ArrayList<>();
        for (int i = 0; i < allEmployee.size(); i++) {
            Employee employee = allEmployee.get(i);
            if(employee.getCheckDuration() == null && employee.getCheckWorkDay() == null){
                noCheckEmployees.add(employee);
            }
        }
        return noCheckEmployees;
    }

    @Override
    public List<Employee> getEmployeeCheckWorkday(int storeId,int workday) {
        List<Employee> allEmployee = getEmployeeByStoreId(storeId);
        List<Employee> workdayEmployee = new ArrayList<>();
        for (int i = 0; i < allEmployee.size(); i++) {
            Employee employee = allEmployee.get(i);
            if (employee.getCheckWorkDay()!= null && weekInCheck(workday, employee.getCheckWorkDay())) {
                workdayEmployee.add(employee);
            }
        }
        return workdayEmployee;
    }

    @Override
    public Time[] getCheckTime(int id) {
        Time startTime = DateUtils.strToTime(employeeMapper.getStartTime(id));
        Time endTime = DateUtils.strToTime(employeeMapper.getEndTime(id));
        Time[] times = {startTime,endTime};
        return times;
    }

    // 判断指定星期是否在该员工的偏好内
    private static boolean weekInCheck(int workday,String check){
        String[] split = check.split(",");
        for (String s : split) {
            if(workday == Integer.parseInt(s))    return true;
        }
        return false;
    }
}
