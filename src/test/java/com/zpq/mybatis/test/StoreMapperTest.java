package com.zpq.mybatis.test;

import com.zpq.mapper.StoreMapper;
import com.zpq.pojo.Store;
import com.zpq.utils.DateUtils;
import com.zpq.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.sql.Time;

public class StoreMapperTest {

    @Test
    public void testSelect(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StoreMapper mapper = sqlSession.getMapper(StoreMapper.class);
        Store store = mapper.getStoreById(1);
        System.out.println(store);
    }

    @Test
    public void testGetSizeById(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StoreMapper mapper = sqlSession.getMapper(StoreMapper.class);
        double size = mapper.getSizeByStoreId(1);
        System.out.println(size);
    }
    @Test
    public void testGetPrepTime(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StoreMapper mapper = sqlSession.getMapper(StoreMapper.class);
        Time prepTime = DateUtils.strToTime(mapper.getPrepTimeByStoreId(1));

        System.out.println(prepTime);
    }
}
