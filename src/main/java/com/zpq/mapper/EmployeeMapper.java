package com.zpq.mapper;

import com.zpq.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    // 根据店铺id查询员工
    List<Employee> getEmployeeByStoreId(@Param("storeId") int storeId);
    // 根据id查询员工偏好的开始时间
    String getStartTime(@Param("id") int id);
    // 根据id查询员工偏好的结束时间
    String getEndTime(@Param("id") int id);

}
