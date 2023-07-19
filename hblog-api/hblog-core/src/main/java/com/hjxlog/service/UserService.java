package com.hjxlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjxlog.domain.User;
import com.hjxlog.protocol.Result;

/**
 * @author hjx
 * @description 针对表【user】的数据库操作Service
 * @createDate 2023-07-04 23:52:10
 */
public interface UserService extends IService<User> {

    Result updateUser(User currentUser, User updateUser);

}
