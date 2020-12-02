package com.icore.winvaz.util.exception;

/**
 * @Deciption 自定义异常，持有错误码
 * @Author wdq
 * @Create 2019/12/18 15:52
 */
public class CodeableException extends WinvazException {

    private static final long serialVersionUID = 1L;

    /** The error code */
    private final ExceptionCodeEnum errorCode;

    /** The addition data */
    private Object data;

    /** The message arguments value */
    private Object[] args;

    /**
     * 创建异常
     *
     * @param errorCode 错误码对象
     */
    public CodeableException(ExceptionCodeEnum errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }

    /**
     * 创建异常
     *
     * @param errorCode 错误码对象
     * @param data 附加的数据
     */
    public CodeableException(ExceptionCodeEnum errorCode, Object data) {
        super(errorCode.name());
        this.errorCode = errorCode;
        this.data = data;
    }

    /**
     * 创建异常
     *
     * @param errorCode 错误码对象
     * @param args 模板参数值
     */
    public CodeableException(ExceptionCodeEnum errorCode, Object[] args) {
        super(errorCode.name());
        this.errorCode = errorCode;
        this.args = args;
    }


    /**
     * 创建异常
     *
     * @param errorCode 错误码对象
     * @param cause 原始异常
     */
    public CodeableException(ExceptionCodeEnum errorCode, Throwable cause) {
        super(errorCode.name(), cause);
        this.errorCode = errorCode;
    }

    /**
     * 返回错误码对象
     *
     * @return 错误码对象
     */
    public ExceptionCodeEnum code() { return this.errorCode; }

    /**
     * 返回附加的数据
     *
     * @return 附加的数据
     */
    public Object data() { return this.data; }

    /**
     * 返回模板参数值
     *
     * @return 模板参数值
     */
    public Object[] args() { return this.args; }

}
