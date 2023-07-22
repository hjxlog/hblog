package com.hjxlog.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.hjxlog.core.annotation.AppLogger;
import com.hjxlog.core.api.dto.UserDto;
import com.hjxlog.core.api.vo.UserVo;
import com.hjxlog.admin.domain.LoginUser;
import com.hjxlog.core.domain.User;
import com.hjxlog.core.protocol.Result;
import com.hjxlog.core.service.UserService;
import com.hjxlog.admin.util.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: Huang JX
 * @date: 2023/7/19
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @GetMapping("/getInfo")
    public Result getInfo() {
        LoginUser currentUser = SecurityUtils.getCurrentUser();
        return Result.success(currentUser.getUser(), UserVo.class);
    }

    @AppLogger
    @PostMapping("/update")
    public Result update(@Valid @RequestBody UserDto dto) {
        // 密码加密
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        LoginUser currentUser = SecurityUtils.getCurrentUser();
        User updateUser = new User();
        BeanUtil.copyProperties(dto, updateUser);
        return userService.updateUser(currentUser.getUser(), updateUser);
    }

}
