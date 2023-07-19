package com.hjxlog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjxlog.api.vo.UserVo;
import com.hjxlog.constant.AdminConstants;
import com.hjxlog.domain.User;
import com.hjxlog.exception.SystemException;
import com.hjxlog.mapper.UserMapper;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author hjx
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-07-04 23:52:10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public Result updateUser(User currentUser, User updateUser) {
        Integer updateUserId = updateUser.getId();
        if (updateUserId == null) {
            throw new SystemException("用户id为空。");
        }
        if (!currentUser.getUsername().equals(updateUser.getUsername())) {
            throw new SystemException("用户名不可修改。");
        }
        saveOrUpdate(updateUser);
        stringRedisTemplate.delete(AdminConstants.REDIS_SIGN_LOGIN_USER + currentUser.getUsername());
        return Result.success(updateUser, UserVo.class);
    }

}
