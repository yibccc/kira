package com.kira.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kira.dao.UserDao;
import com.kira.domain.User;
import com.kira.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author Kira
 * @create 2023-01-280:04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
}
