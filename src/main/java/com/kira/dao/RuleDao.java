package com.kira.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kira.domain.Rule;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Kira
 * @create 2023-02-11 16:55
 */
@Mapper
public interface RuleDao extends BaseMapper<Rule> {
}
