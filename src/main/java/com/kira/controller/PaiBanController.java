package com.kira.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kira.controller.utils.R;
import com.kira.domain.*;
import com.kira.domain.PaiBan;
import com.kira.dto.PaiBanDto;
import com.kira.service.IEmployeeService;
import com.kira.service.IJobService;
import com.kira.service.IPaiBanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kira
 * @create 2023-02-12 15:21
 */
@RestController
@RequestMapping("/paiban")
@Slf4j
public class PaiBanController {
    @Autowired
    private IPaiBanService paiBanService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IJobService jobService;
    //输出排班表（日）
    @GetMapping("/list/day")
    private R<Page> printDay(Integer storeId,String date){
        int pageSize=10000000;
        int page = 1;
        //分页构造器
        Page<PaiBan> pageInfo = new Page(page,pageSize);
        Page<PaiBanDto> paiBanDtoPage = new Page<>();
        //条件构造器
        LambdaQueryWrapper<PaiBan> queryWrapper = new LambdaQueryWrapper();
        //过滤条件
        queryWrapper.eq(PaiBan::getStoreId, storeId);
        queryWrapper.eq(PaiBan::getDate,date);
        //查询操作
        paiBanService.page(pageInfo, queryWrapper);

        BeanUtils.copyProperties(pageInfo,paiBanDtoPage,"records");

        List<PaiBan> records = pageInfo.getRecords();

        List<PaiBanDto> list = records.stream().map((item)->{
            PaiBanDto paiBanDto =new PaiBanDto();

            BeanUtils.copyProperties(item,paiBanDto);

            Integer jobId = item.getJobId();
            Integer employeeId = item.getEmployeeId();

            Job job = jobService.getById(jobId);
            String jobName = job.getName();
            paiBanDto.setJobName(jobName);

            Employee employee = employeeService.getById(employeeId);
            String employeeName = employee.getName();
            paiBanDto.setEmployeeName(employeeName);
            paiBanDto.setWeek(date);
            return paiBanDto;
        }).collect(Collectors.toList());
        paiBanDtoPage.setRecords(list);

        return R.success(paiBanDtoPage);

    }
    //输出排班表（周）
    @GetMapping("/list/week")
    private R<Page> printWeek(){
        return null;
    }
    //根据日期判断周几
    public static Integer dateToWeek(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer[] weekDays = {7,1,2,3,4,5,6};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = sdf.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w];
    }

}
