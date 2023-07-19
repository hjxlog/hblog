package com.hjxlog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.hjxlog.api.dto.UserDto;
import com.hjxlog.api.vo.UserVo;
import com.hjxlog.domain.LoginUser;
import com.hjxlog.domain.User;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.UserService;
import com.hjxlog.util.SecurityUtils;
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
