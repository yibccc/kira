package com.kira.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kira.controller.utils.R;
import com.kira.domain.Rule;
import com.kira.domain.Rule;
import com.kira.service.IRuleService;
import com.kira.service.Imp.RuleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author Kira
 * @create 2023-02-11 16:59
 */
@Slf4j
@RestController
@RequestMapping("/rules")
public class RuleController {
    @Autowired
    private IRuleService ruleService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page = {},pageSize = {},name = {}",page,pageSize,name);
        //分页构造器
        Page pageInfo = new Page(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Rule> queryWrapper = new LambdaQueryWrapper();
        //过滤条件
        queryWrapper.like(StringUtils.hasText(name),Rule::getName, name);
        //查询操作
        ruleService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping
    public R<String> save(@RequestBody Rule rule){
        log.info("新增规则，规则信息：｛｝",rule.toString());
        ruleService.save(rule);
        return R.success("新增成功");
    }
    @DeleteMapping
    public R<String> deleteByIds(String id){
        String[] nums = id.split(",");
        for(int  c=0;c<nums.length;c++) {
            ruleService.removeById(nums[c]);
        }
        return R.success("删除成功");
    }
    //根据id修改
    @PutMapping
    public R<String> update(@RequestBody Rule rule){
        log.info(rule.toString());
        ruleService.updateById(rule);
        return R.success("修改成功！");
    }

    //回显
    @GetMapping("/{id}")
    public R<Rule> getById(@PathVariable Integer id){
        Rule rule = ruleService.getById(id);
        return R.success(rule);
    }
}
