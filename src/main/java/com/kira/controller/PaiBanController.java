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
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
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

    //输出排班表
    @GetMapping("/list")
    @Cacheable(value = "paibanDateCache",key = "#date")
    public R<Page> printDay(Integer storeId, String date,Integer selectJobId) throws ParseException {
        String dateEnd = getLastDayOfWeek(date);

        //分页构造器
        Page<PaiBan> pageInfo = new Page();
        Page<PaiBanDto> paiBanDtoPage = new Page<>();

        //条件构造器
        LambdaQueryWrapper<PaiBan> queryWrapper = new LambdaQueryWrapper();
        //过滤条件
        queryWrapper.eq(PaiBan::getStoreId, storeId);
        queryWrapper.between(PaiBan::getDate,date,dateEnd);
        if (null != selectJobId){
            queryWrapper.eq(PaiBan::getJobId,selectJobId);
        }
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
            return paiBanDto;
        }).collect(Collectors.toList());
        paiBanDtoPage.setRecords(list);

        return R.success(paiBanDtoPage);
    }

    //修改排班
    @PutMapping
    @CacheEvict(value = "paibanDateCache",allEntries = true)
    public R<String> update(@RequestBody PaiBan paiBan){
        paiBanService.updateById(paiBan);
        return R.success("修改成功");
    }

    //delete
    @DeleteMapping
    @CacheEvict(value = "paibanDateCache",allEntries = true)
    public R<String> deleteByIds(String id){
        String[] nums = id.split(",");
        for(int  c=0;c<nums.length;c++) {
            paiBanService.removeById(nums[c]);
        }
        return R.success("删除成功");
    }
    //新增
    @PostMapping
    @CacheEvict(value = "paibanDateCache",allEntries = true)
    public R<String> save(@RequestBody PaiBan paiBan){
        paiBanService.save(paiBan);
        return R.success("添加成功");
    }
    /**
     * 获取指定日期所在周的周日
     *
     * @param date 日期
     */
    public static String getLastDayOfWeek(String date) throws ParseException {
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date start = fmt.parse(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        c.add(Calendar.DATE, 7 - c.get(Calendar.DAY_OF_WEEK) + 1);
        Date sundayDate = c.getTime();
        String weekEnd = sdf.format(sundayDate);
        return weekEnd;
    }



}
