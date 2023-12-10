package com.hjxlog.handler;

import com.hjxlog.exception.SystemException;
import com.hjxlog.protocol.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: Huang JX
 * @date: 2023/7/1
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({SystemException.class})
    public Result systemExceptionHandler(SystemException e) {
        log.error("发生异常：{}", e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public Result methodArgumentNotValidException(BindException e) {
        log.error("发生异常：{}", e.getMessage());
        StringBuilder stringBuilder = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(objectError -> stringBuilder.append(objectError.getDefaultMessage()));
        return Result.fail("500", stringBuilder.toString());
    }

}
