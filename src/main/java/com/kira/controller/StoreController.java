package com.kira.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kira.controller.utils.R;
import com.kira.domain.Employee;
import com.kira.domain.Store;
import com.kira.service.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kira
 * @create 2023-01-2916:01
 */
@Slf4j
@RestController
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    private IStoreService storeService;

    //查询分类数据
    @GetMapping("/list")
    public R<List<Store>> list(){
        LambdaQueryWrapper<Store> queryWrapper= new LambdaQueryWrapper<>();
        List<Store> list = storeService.list(queryWrapper);
        return R.success(list);
    }

    //add
    @PostMapping
    public R<String> save(@RequestBody Store store){
        log.info("新增商店，商店信息：｛｝",store.toString());
        if("" == store.getPrepTime()){
            store.setPrepTime("01:00:00");
        }
        if ("" == store.getClosureTime()){
            store.setClosureTime("02:00:00");
        }
        if (null == store.getDutyNum()){
            store.setDutyNum(2);
        }
        if (null == store.getEachCleanArea()){
            store.setEachCleanArea(80);
        }
        if (null == store.getEachManageArea()){
            store.setEachManageArea(100);
        }
        if (0 == store.getEachServe()){
            store.setEachServe((float) 3.8);
        }
        storeService.save(store);
        return R.success("新增成功");
    }

    //分页查询
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("page = {},pageSize = {},name = {}",page,pageSize,name);
        //分页构造器
        Page pageInfo = new Page(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper();
        //过滤条件
        queryWrapper.like(StringUtils.hasText(name),Store::getName, name);
        //查询操作
        storeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

//    //删除门店
//    @DeleteMapping
//    public R<String> deleteById(Integer id){
//        storeService.removeById(id);
//        return R.success("删除成功");
//    }
    //批量删除
    @DeleteMapping
    public R<String> deleteByIds(String id){
        String[] nums = id.split(",");
        for(int  c=0;c<nums.length;c++) {
            storeService.remove(Integer.parseInt(nums[c]));
        }
        return R.success("删除成功");
    }
    //根据id修改
    @PutMapping
    @CacheEvict(value = "paibanDateCache",allEntries = true)
    public R<String> update(@RequestBody Store store){
        log.info(store.toString());
        storeService.updateById(store);
        return R.success("修改成功！");
    }

    //回显
    @GetMapping("/{id}")
    public R<Store> getById(@PathVariable Integer id){
        Store store = storeService.getById(id);
        return R.success(store);
    }

    //查询规则
    @GetMapping("/rules/{id}")
    public R<Store> getRulesById(@PathVariable Integer id){
        Store store = storeService.getById(id);
        return R.success(store);
    }
}
