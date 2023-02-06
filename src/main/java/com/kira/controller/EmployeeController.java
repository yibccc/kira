package com.kira.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kira.controller.utils.R;
import com.kira.domain.Employee;
import com.kira.domain.Store;
import com.kira.domain.User;
import com.kira.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author shkstart
 * @create 2023-01-2715:16
 */
@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    //分页查询
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page = {},pageSize = {},name = {}",page,pageSize,name);
        //分页构造器
        Page pageInfo = new Page(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //过滤条件
        queryWrapper.like(StringUtils.hasText(name),Employee::getName, name);
        //查询操作
        employeeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    //根据id修改
    @PutMapping
    public R<String> update(@RequestBody Employee employee){
        log.info(employee.toString());
        employeeService.updateById(employee);
        return R.success("修改成功！");
    }

    //根据id删除
    @DeleteMapping
    public R<String> deleteById(Integer id){
        employeeService.removeById(id);
        return R.success("删除成功");
    }

    //新增
    @PostMapping
    public R<String> save(@RequestBody Employee employee){
        log.info("新增员工，员工信息：",employee.toString());
        employeeService.save(employee);
        return R.success("新增成功");
    }

    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Integer id){
        Employee employee  = employeeService.getById(id);
        return R.success(employee);
    }
}
