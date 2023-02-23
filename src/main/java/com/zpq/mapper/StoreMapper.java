package com.zpq.mapper;

import com.zpq.pojo.Store;
import org.apache.ibatis.annotations.Param;


public interface StoreMapper {
    // 根据商店id查询信息
    Store getStoreById(@Param("storeId") Integer storeId);
    double getSizeByStoreId(@Param("storeId") Integer storeId);
    // 获取店铺开店前每个员工能服务的区域大小
    int getEachAreaByStoreId(@Param("storeId") int storeId);
    // 获取闭店后每个员工清理的区域
    int getEachCleanByStoreId(@Param("storeId") int storeId);
    // 获取每个员工服务的人数
    float getEachServeByStoreId(@Param("storeId") int storeId);
    // 客流量为0时的值班人数；
    int getDutyNumByStoreId(@Param("storeId") int storeId);
    // 获取开店前的准备时间
    String getPrepTimeByStoreId(@Param("storeId") int storeId);
    // 获取关店前的清理时间
    String getClosureTimeByStoreId(@Param("storeId") int storeId);
}
