package com.zpq.mybatis.test;

import com.zpq.mapper.EmployeeMapper;
import com.zpq.service.EmployeeService;
import com.zpq.service.impl.EmployeeServiceImpl;
import com.zpq.pojo.Employee;
import com.zpq.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class EmployeeTest {

    @Test
    public void testSelect(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employeeByStoreId = employeeMapper.getEmployeeByStoreId(1);
        System.out.println(employeeByStoreId);
    }

    @Test
    public void testSelectNoCheckEmployee(){
        EmployeeService employeeService = new EmployeeServiceImpl();
        System.out.println(employeeService.getEmployeeNotCheck(1));
        System.out.println(employeeService.getEmployeeNotCheck(2));
        System.out.println(employeeService.getEmployeeCheckWorkday(1,1));
    }

    @Test
    public void testEmployeeTime(){
        EmployeeService employeeService = new EmployeeServiceImpl();
        System.out.println(Arrays.toString(employeeService.getCheckTime(1)));
    }
}
