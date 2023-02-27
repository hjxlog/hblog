package com.hjxlog.protocol;

/**
 * @author: Huang JX
 * @date: 2023/2/27
 */
public interface ResultMessage {

    /**
     * 获取异常code
     *
     * @return code
     */
    String getCode();

    /**
     * 获取异常message.
     *
     * @return message
     */
    String getMessage();

    /**
     * 获取异常message
     *
     * @param args 参数
     * @return message
     */
    String getMessage(Object... args);

}

