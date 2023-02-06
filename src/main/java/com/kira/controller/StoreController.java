package com.kira.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kira.controller.utils.R;
import com.kira.domain.Employee;
import com.kira.domain.Store;
import com.kira.service.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shkstart
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

    //删除门店
    @DeleteMapping
    public R<String> deleteById(Integer id){
        storeService.removeById(id);
        return R.success("删除成功");
    }

    //根据id修改
    @PutMapping
    public R<String> update(@RequestBody Store store){
        log.info(store.toString());
        storeService.updateById(store);
        return R.success("修改成功！");
    }

    @GetMapping("/{id}")
    public R<Store> getById(@PathVariable Integer id){
        Store store = storeService.getById(id);
        return R.success(store);
    }
}
