package com.zpq.mapper;

import com.zpq.pojo.Sailing;
import org.apache.ibatis.annotations.Param;

public interface SailingMapper {
    // 增添排班信息
    public void addSailing(@Param("sailing") Sailing sailing);
}
