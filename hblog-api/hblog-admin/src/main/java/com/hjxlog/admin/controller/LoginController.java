package com.hjxlog.admin.controller;

import com.hjxlog.admin.service.LoginService;
import com.hjxlog.core.annotation.AppLogger;
import com.hjxlog.core.domain.User;
import com.hjxlog.core.protocol.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Huang JX
 * @date: 2023/7/5
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @AppLogger
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return loginService.login(user);
    }

    @AppLogger
    @RequestMapping("/logout")
    public Result logout() {
        return loginService.logout();
    }

}
