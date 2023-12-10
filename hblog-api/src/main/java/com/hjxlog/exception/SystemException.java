package com.hjxlog.exception;

import lombok.Data;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@Data
public class SystemException extends RuntimeException {

    private String code;
    private String message;

    public SystemException(String message) {
        super(message);
        this.message = message;
    }

    public SystemException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
