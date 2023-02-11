package com.kira.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kira.dao.RuleDao;
import com.kira.domain.Rule;
import com.kira.service.IRuleService;
import org.springframework.stereotype.Service;

/**
 * @author Kira
 * @create 2023-02-11 16:56
 */
@Service
public class RuleServiceImpl extends ServiceImpl<RuleDao, Rule> implements IRuleService {
}
