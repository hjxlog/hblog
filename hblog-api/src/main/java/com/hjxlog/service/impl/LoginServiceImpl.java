package com.hjxlog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.hjxlog.api.vo.UserVo;
import com.hjxlog.constant.SystemConstants;
import com.hjxlog.domain.User;
import com.hjxlog.protocol.Result;
import com.hjxlog.domain.LoginUser;
import com.hjxlog.service.LoginService;
import com.hjxlog.util.JWTUtils;
import com.hjxlog.util.SecurityUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: Huang JX
 * @date: 2023/7/5
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误!");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        stringRedisTemplate.opsForValue().set(SystemConstants.REDIS_SIGN_LOGIN_USER + loginUser.getUsername(), JSONUtil.toJsonStr(loginUser));
        String jwt = JWTUtils.createToken(loginUser.getUsername());
        Map<String, Object> map = new HashMap<>();
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(loginUser.getUser(), userVo);
        map.put("token", jwt);
        map.put("user", userVo);
        map.put("nickname", loginUser.getNickname());
        return Result.success(map);
    }

    @Override
    public Result logout() {
        LoginUser currentUser = SecurityUtils.getCurrentUser();
        stringRedisTemplate.delete(SystemConstants.REDIS_SIGN_LOGIN_USER + currentUser.getUsername());
        return Result.success();
    }

}
