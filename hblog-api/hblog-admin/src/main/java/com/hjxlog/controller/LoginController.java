package com.hjxlog.controller;

import com.hjxlog.domain.User;
import com.hjxlog.protocol.Result;
import com.hjxlog.service.LoginService;
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

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return loginService.login(user);
    }

    @RequestMapping("/logout")
    public Result logout() {
        return loginService.logout();
    }

}