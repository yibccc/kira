package com.kira.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kira.dao.EmployeeDao;
import com.kira.domain.Employee;
import com.kira.service.IEmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author shkstart
 * @create 2023-01-2715:11
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao, Employee> implements IEmployeeService {

//    @Autowired
//    private EmployeeDao employeeDao;
//    @Override
//    public IPage<Employee> getPage(int currentPage, int pageSize) {
//        IPage page = new Page(currentPage,pageSize);
//        employeeDao.selectPage(page,null);
//        return page;
//    }

}
