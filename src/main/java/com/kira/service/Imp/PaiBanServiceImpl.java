package com.kira.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kira.dao.PaiBanDao;
import com.kira.domain.PaiBan;
import com.kira.service.IPaiBanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Kira
 * @create 2023-02-12 15:21
 */
@Service
public class PaiBanServiceImpl extends ServiceImpl<PaiBanDao,PaiBan> implements IPaiBanService {
}
