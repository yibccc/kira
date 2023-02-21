package com.kira.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kira.controller.utils.R;
import com.kira.domain.*;
import com.kira.dto.EmployeeDto;
import com.kira.service.IEmployeeService;
import com.kira.service.IJobService;
import com.kira.service.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kira
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

//    //根据id删除
//    @DeleteMapping
//    public R<String> deleteById(Integer id){
//        employeeService.removeById(id);
//        return R.success("删除成功");
//    }

//    批量删除
    @DeleteMapping
    public R<String> deleteByIds(String id){
        String[] nums = id.split(",");
        for(int  c=0;c<nums.length;c++) {
            employeeService.removeById(nums[c]);
        }
        return R.success("删除成功");
    }

    //新增
    @PostMapping
    public R<String> save(@RequestBody Employee employee){
        log.info("新增员工，员工信息：",employee.toString());
//        if(null == employee.getCheckDuration()){
//            employee.setCheckDuration(4);
//        }
        if("".equals(employee.getCheckWorkday())){
            employee.setCheckWorkday(null);
        }
        if("".equals(employee.getStartTime())){
            employee.setStartTime(null);
        }
        if ("".equals(employee.getEndTime())){
            employee.setEndTime(null);
        }
        if (null == (employee.getPassword())){
            employee.setPassword("123456");
        }
        employeeService.save(employee);
        return R.success("新增成功");
    }


    //回显
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Integer id){
        Employee employee  = employeeService.getById(id);
        return R.success(employee);
    }

    //查询偏好
    @GetMapping("/flavors/{id}")
    public R<Employee> getFlavorsById(@PathVariable Integer id){
        Employee employee = employeeService.getById(id);
        if(null == employee.getCheckDuration()){
            employee.setCheckDuration(4);
        }
        if(null == employee.getCheckWorkday()){
            employee.setCheckWorkday("1,2,3,4,5,6,7");
        }
        if(null == employee.getStartTime()){
            employee.setStartTime("06:00:00");
        }
        if (null == employee.getEndTime()){
            employee.setEndTime("22:00:00");
        }
        return R.success(employee);
    }

    //用户列表
    @GetMapping("/list")
    public R<List<Employee>> list(String date,String startTime,String endTime){
        //条件构造器
        LambdaQueryWrapper<Employee> queryWrapper= new LambdaQueryWrapper<>();
        //过滤条件 1.不能连续工作4小时，2不能一天工作8小时，3.一周不能工作超过40小时。

        List<Employee> list = employeeService.list(queryWrapper);
        return R.success(list);
    }

    //员工登录
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //login
        String password = employee.getPassword();

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getEmail,employee.getEmail());
        Employee one = employeeService.getOne(queryWrapper);

        if(one == null){
            return R.error("登录失败");
        }

        if(!one.getPassword().equals(password)){
            return R.error("密码错误");
        }

        request.getSession().setAttribute("user",one.getId());
        return R.success(one);
    }

    //login out
    @PostMapping("/logout")
    public R<String> loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    //修改密码
    @PutMapping("/editPwd")
    public R<String> updatePwd(@RequestBody Employee employee){
        employeeService.updateById(employee);
        return R.success("修改成功！");
    }
}
