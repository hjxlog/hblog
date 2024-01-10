package com.hjxlog.util;

import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Huang JX
 * @date: 2023/7/22
 */
@Component
public class RedisUtils<T> {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void saveList(String key, T obj) {
        stringRedisTemplate.opsForList().leftPush(key, JSONUtil.toJsonStr(obj));
    }

    public List<T> getList(String key, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        List<String> jsonList = stringRedisTemplate.opsForList().range(key, 0, -1);
        jsonList.forEach(jsonStr -> list.add(JSONUtil.toBean(jsonStr, clazz)));
        return list;
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public void save(String key, T obj) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(obj));
    }

    public <T> T getValue(String key, Class<T> clazz) {
        String jsonStr = stringRedisTemplate.opsForValue().get(key);
        return jsonStr != null ? JSONUtil.toBean(jsonStr, clazz) : null;
    }

}
