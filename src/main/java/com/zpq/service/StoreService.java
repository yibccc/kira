package com.zpq.service;

import java.sql.Time;

public interface StoreService {
    // 获取并加工准备时间
    Time getPrepTime(int storeId);
    // 获取并加工完工后的加工时间
    Time getClosureTime(int storeId);
}
