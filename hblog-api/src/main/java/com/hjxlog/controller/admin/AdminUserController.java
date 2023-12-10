package com.hjxlog.controller.admin;

import com.hjxlog.annotation.AppLogger;
import com.hjxlog.api.dto.UserInfoDto;
import com.hjxlog.api.dto.UserUpdatePasswordDto;
import com.hjxlog.api.vo.UserVo;
import com.hjxlog.domain.LoginUser;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.AdminUserService;
import com.hjxlog.util.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: Huang JX
 * @date: 2023/7/19
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

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
