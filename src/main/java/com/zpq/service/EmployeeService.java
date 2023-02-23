package com.zpq.service;

import com.zpq.pojo.Employee;

import java.sql.Time;
import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployeeByStoreId(int storeId);
    // 获取没有特殊要求的员工集合
    List<Employee> getEmployeeNotCheck(int storeId);
    // 获取有指定星期要求的员工集合
    List<Employee> getEmployeeCheckWorkday(int storeId,int workday);
    // 获取员工偏好的开始时间和结束时间
    Time[] getCheckTime(int id);
}
