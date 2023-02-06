package com.kira.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kira.dao.StoreDao;
import com.kira.domain.Store;
import com.kira.service.IStoreService;
import org.springframework.stereotype.Service;

/**
 * @author shkstart
 * @create 2023-01-2715:09
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreDao, Store> implements IStoreService {
}
