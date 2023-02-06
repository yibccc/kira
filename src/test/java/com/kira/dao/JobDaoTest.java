package com.kira.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kira.domain.Employee;
import com.kira.domain.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author shkstart
 * @create 2023-01-2621:27
 */
@SpringBootTest
public class JobDaoTest {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private JobDao jobDao;

    @Test
    void testGetById(){
        System.out.println(employeeDao.selectList(null));
    }

    @Test
    void testGetPage(){
        IPage page = new Page(2,1);
        jobDao.selectPage(page,null);

    }
    @Test
    void testGetBy(){
        QueryWrapper<Job> qw = new QueryWrapper<>();
        qw.like("name","经理");
        jobDao.selectList(qw);
    }

}
