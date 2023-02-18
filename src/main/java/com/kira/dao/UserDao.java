package com.kira.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kira.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Kira
 * @create 2023-01-280:04
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
