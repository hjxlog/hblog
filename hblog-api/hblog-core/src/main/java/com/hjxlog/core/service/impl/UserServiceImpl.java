package com.hjxlog.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.core.domain.User;
import com.hjxlog.core.mapper.UserMapper;
import com.hjxlog.core.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author hjx
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-07-04 23:52:10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
