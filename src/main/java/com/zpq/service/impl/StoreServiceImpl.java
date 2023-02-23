package com.zpq.service.impl;

import com.zpq.mapper.StoreMapper;
import com.zpq.service.StoreService;
import com.zpq.utils.DateUtils;
import com.zpq.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.Time;

public class StoreServiceImpl implements StoreService {
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    StoreMapper storeMapper = sqlSession.getMapper(StoreMapper.class);
    @Override
    public Time getPrepTime(int storeId) {
        Time prepTime = DateUtils.strToTime(storeMapper.getPrepTimeByStoreId(storeId));
        return prepTime;
    }

    @Override
    public Time getClosureTime(int storeId) {
        Time closureTime = DateUtils.strToTime(storeMapper.getClosureTimeByStoreId(storeId));
        return closureTime;
    }
}
