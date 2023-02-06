package com.kira.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kira.dao.JobDao;
import com.kira.domain.Job;
import com.kira.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shkstart
 * @create 2023-01-2714:46
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobDao,Job> implements IJobService {

}
