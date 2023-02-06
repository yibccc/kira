package com.kira.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kira.controller.utils.R;
import com.kira.domain.Job;
import com.kira.domain.Store;
import com.kira.service.IJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shkstart
 * @create 2023-01-2715:12
 */
@Slf4j
@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private IJobService jobService;

    @GetMapping("/list")
    public R<List<Job>> list(){
        LambdaQueryWrapper<Job> queryWrapper= new LambdaQueryWrapper<>();
        List<Job> list = jobService.list(queryWrapper);
        return R.success(list);
    }


}
