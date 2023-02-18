package com.kira.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kira.common.CustomException;
import com.kira.dao.StoreDao;
import com.kira.domain.Employee;
import com.kira.domain.Store;
import com.kira.service.IEmployeeService;
import com.kira.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kira
 * @create 2023-01-2715:09
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreDao, Store> implements IStoreService {
    @Autowired
    private IEmployeeService employeeService;
    @Override
    public void remove(int id) {

        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();

        employeeLambdaQueryWrapper.eq(Employee::getStoreId,id);
        int count = employeeService.count(employeeLambdaQueryWrapper);

        if (count > 0){
            throw new CustomException("不可删除，此门店还有员工存在");
        }
        super.removeById(id);
    }
}
