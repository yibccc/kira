package com.kira.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kira.controller.utils.R;
import com.kira.domain.Employee;

/**
 * @author Kira
 * @create 2023-01-2715:11
 */
public interface IEmployeeService extends IService<Employee> {

    //    @Autowired
    //    private EmployeeDao employeeDao;
    //    @Override
    //    public IPage<Employee> getPage(int currentPage, int pageSize) {
    //        IPage page = new Page(currentPage,pageSize);
    //        employeeDao.selectPage(page,null);
    //        return page;
    //    }

    void removeWithPaiban(int id);
}
