package com.kira.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kira.controller.utils.R;
import com.kira.domain.Employee;
import com.kira.domain.Job;
import com.kira.domain.Store;
import com.kira.domain.User;
import com.kira.dto.EmployeeDto;
import com.kira.service.IEmployeeService;
import com.kira.service.IJobService;
import com.kira.service.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private IJobService jobService;

    @Autowired
    private IStoreService storeService;

    //分页查询
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page = {},pageSize = {},name = {}",page,pageSize,name);
        //分页构造器
        Page<Employee> pageInfo = new Page(page,pageSize);
        Page<EmployeeDto> employeeDtoPage = new Page<>();
        //条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //过滤条件
        queryWrapper.like(StringUtils.hasText(name),Employee::getName, name);
        //查询操作
        employeeService.page(pageInfo, queryWrapper);

        BeanUtils.copyProperties(pageInfo,employeeDtoPage,"records");

        List<Employee> records = pageInfo.getRecords();

        List<EmployeeDto> list = records.stream().map((item)->{
            EmployeeDto employeeDto =new EmployeeDto();

            BeanUtils.copyProperties(item,employeeDto);

            Integer jobId = item.getJobId();
            Integer storeId = item.getStoreId();

            Job job = jobService.getById(jobId);
            String jobName = job.getName();
            employeeDto.setJobName(jobName);

            Store store = storeService.getById(storeId);
            String storeName = store.getName();
            employeeDto.setStoreName(storeName);

            return employeeDto;
                }).collect(Collectors.toList());
        employeeDtoPage.setRecords(list);

        return R.success(employeeDtoPage);
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
