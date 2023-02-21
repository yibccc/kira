package com.kira.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kira.controller.utils.R;
import com.kira.dao.EmployeeDao;
import com.kira.domain.Employee;
import com.kira.domain.PaiBan;
import com.kira.dto.PaiBanDto;
import com.kira.service.IEmployeeService;
import com.kira.service.IPaiBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kira
 * @create 2023-01-2715:11
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao, Employee> implements IEmployeeService {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPaiBanService paiBanService;

    @Override
    public void remove(int id) {
        employeeService.removeById(id);

        LambdaQueryWrapper<PaiBan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PaiBan::getEmployeeId,id);

        paiBanService.remove(queryWrapper);
    }


//    @Override
//    public IPage<Employee> getPage(int currentPage, int pageSize) {
//        IPage page = new Page(currentPage,pageSize);
//        employeeDao.selectPage(page,null);
//        return page;
//    }

}
