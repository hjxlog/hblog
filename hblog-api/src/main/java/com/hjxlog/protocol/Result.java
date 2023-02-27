package com.hjxlog.protocol;

import lombok.Data;

/**
 * @author: Huang JX
 * @date: 2023/2/27
 */
@Data
public class Result<T> {

    public static final String FAILURE = "500";

    public static final String SUCCESSFUL = "0";

    private String code ;

    private String msg ;

    private T body;

    public Result() {
        this.code = this.SUCCESSFUL;
        this.msg = "请求处理成功";
    }

    public Result(T body) {
        this();
        this.body = body;
    }

    public Result(ResultMessage message, Object... args) {
        this.code = message.getCode();
        this.msg = message.getMessage(args);
    }

    public Result(ResultMessage message) {
        this(message, null);
    }

    public void setMsg(String message) {
        this.msg = message;
    }

    public void setMsg(ResultMessage message) {
        this.code = message.getCode();
        this.msg = message.getMessage();
    }

    public void setMsg(ResultMessage message, Object... args) {
        this.code = message.getCode();
        this.msg = message.getMessage(args);
    }

    public static <T> Result<T> success(T body) {
        return new Result(body);
    }

    public static <T> Result<T> fail(String code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }

}