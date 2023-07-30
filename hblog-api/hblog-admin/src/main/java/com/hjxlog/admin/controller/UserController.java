package com.hjxlog.admin.controller;

import com.hjxlog.admin.domain.LoginUser;
import com.hjxlog.admin.service.AdminUserService;
import com.hjxlog.admin.util.SecurityUtils;
import com.hjxlog.core.annotation.AppLogger;
import com.hjxlog.core.api.dto.UserInfoDto;
import com.hjxlog.core.api.dto.UserUpdatePasswordDto;
import com.hjxlog.core.api.vo.UserVo;
import com.hjxlog.core.protocol.Result;
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
    private AdminUserService adminUserService;

    @GetMapping("/getInfo")
    public Result getInfo() {
        LoginUser currentUser = SecurityUtils.getCurrentUser();
        return Result.success(currentUser.getUser(), UserVo.class);
    }

    @AppLogger
    @PostMapping("/updateInfo")
    public Result updateInfo(@Valid @RequestBody UserInfoDto dto) {
        UserVo userVo = adminUserService.updateInfo(dto);
        return Result.success(userVo);
    }

    @PostMapping("/updatePassword")
    public Result updatePassword(@Valid @RequestBody UserUpdatePasswordDto dto) {
        UserVo userVo = adminUserService.updatePassword(dto);
        return Result.success(userVo);
    }

}
