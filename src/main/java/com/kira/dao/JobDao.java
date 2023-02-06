package com.kira.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kira.domain.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author shkstart
 * @create 2023-01-2621:19
 */
@Mapper
public interface JobDao extends BaseMapper<Job> {


}
