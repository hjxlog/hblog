package com.hjxlog.protocol;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;

import java.util.Collection;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@Data
public class Result<T> {

    public static final String FAILURE = "500";

    public static final String SUCCESSFUL = "200";

    private String code;

    private String msg;

    private T body;

    public Result() {
        this.code = this.SUCCESSFUL;
        this.msg = "请求处理成功。";
    }

    public Result(T body) {
        this();
        this.body = body;
    }

    public static Result success() {
        return new Result();
    }

    public static <T> Result<T> success(T body) {
        return new Result(body);
    }

    public static <T> Result<T> success(T body, Class clazz) {
        return new Result(BeanUtil.copyProperties(body, clazz));
    }

    public static <T> Result<T> success(Collection<T> body, Class clazz) {
        return new Result(BeanUtil.copyToList(body, clazz));
    }

    public static <T> Result<T> fail(String code, String message) {
        Result<T> result = new Result<>();
        result.setCode(StringUtils.isNotBlank(code) ? code : FAILURE);
        result.setMsg(message);
        return result;
    }

}
