package com.kira.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kira.domain.Employee;

/**
 * @author shkstart
 * @create 2023-01-2715:11
 */
public interface IEmployeeService extends IService<Employee> {
    IPage<Employee> getPage(int currentPage,int pageSize);
}
