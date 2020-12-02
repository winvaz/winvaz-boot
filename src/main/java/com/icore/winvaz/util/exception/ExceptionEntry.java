package com.icore.winvaz.util.exception;

/**
 * @Deciption 异常错误码和对应的消息的定义
 * @Author wdq
 * @Create 2020/8/7 14:12
 * @Version 1.0.0
 */
public class ExceptionEntry {

    private ExceptionCodeEnum code;

    private String message;

    public ExceptionCodeEnum getCode() {
        return code;
    }

    public void setCode(ExceptionCodeEnum code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
