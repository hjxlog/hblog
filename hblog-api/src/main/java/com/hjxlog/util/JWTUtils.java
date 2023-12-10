package com.hjxlog.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.hjxlog.constant.SystemConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Huang JX
 * @date: 2023/7/5
 */
public class JWTUtils {

    public static String createToken(String username) {
        Map<String, Object> payload = new HashMap<>();
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.HOUR, 12);
        // 签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        // 过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        // 载荷
        payload.put(SystemConstants.JWT_PAYLOAD_USERNAME, username);
        // 秘钥
        String key = SystemConstants.JWT_KEY;
        String jwt = JWTUtil.createToken(payload, key.getBytes());
        return jwt;
    }

}
