package com.hjxlog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hjxlog.api.dto.UserInfoDto;
import com.hjxlog.api.dto.UserUpdatePasswordDto;
import com.hjxlog.api.vo.UserVo;
import com.hjxlog.domain.User;
import com.hjxlog.exception.SystemException;
import com.hjxlog.service.UserService;
import com.hjxlog.service.AdminUserService;
import com.hjxlog.util.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Huang JX
 * @date: 2023/7/23
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserVo updateInfo(UserInfoDto dto) {
        Integer id = dto.getId();
        if (SecurityUtils.isCurrentUser(id)) {
            throw new SystemException("非当前用户。");
        }
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper();
        wrapper.eq(User::getId, id);
        wrapper.set(User::getNickname, dto.getNickname());
        return updateUserByWrapper(id, wrapper);
    }

    @Override
    public UserVo updatePassword(UserUpdatePasswordDto dto) {
        Integer id = dto.getId();
        if (!SecurityUtils.isCurrentUser(id)) {
            throw new SystemException("非当前用户。");
        }
        if (!passwordEncoder.matches(dto.getOldPassword(), SecurityUtils.getCurrentUser().getPassword())) {
            throw new SystemException("旧密码错误。");
        }
        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new SystemException("输入新密码与确认新密码不一致。");
        }
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper();
        wrapper.eq(User::getId, id);
        wrapper.set(User::getPassword, passwordEncoder.encode(dto.getNewPassword()));
        return updateUserByWrapper(id, wrapper);
    }

    private UserVo updateUserByWrapper(Integer userId, LambdaUpdateWrapper<User> wrapper) {
        boolean isSuccess = userService.update(wrapper);
        if (!isSuccess) {
            throw new SystemException("更新失败。");
        }
        User user = userService.getById(userId);
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(user, userVo);
        return userVo;
    }

}
