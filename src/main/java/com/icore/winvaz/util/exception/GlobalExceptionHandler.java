package com.icore.winvaz.util.exception;

import com.icore.winvaz.restapi.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Deciption 全局异常处理
 * @Author wdq
 * @Create 2020/8/7 13:59
 * @Version 1.0.0
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CodeException.class)
    public Result handle(CodeException e) {
        return Result.failure(e.code().code(), e.getMessage());
    }
}
