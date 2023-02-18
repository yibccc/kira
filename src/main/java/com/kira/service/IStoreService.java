package com.kira.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kira.domain.Store;

/**
 * @author Kira
 * @create 2023-01-2715:08
 */
public interface IStoreService extends IService<Store> {
    public void remove(int id);
}
