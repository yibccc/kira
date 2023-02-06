package com.kira.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kira.domain.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author shkstart
 * @create 2023-01-2622:13
 */
@Mapper
public interface EmployeeDao extends BaseMapper<Employee> {
}
