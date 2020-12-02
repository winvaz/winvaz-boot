package com.icore.winvaz.util.exception;

/**
 * @Author wdq
 * @Create 2019/12/18 15:49
 */
public class CodeException extends CodeableException{

    private static final long serialVersionUID = 1L;

    /**
     * 创建异常
     *
     * @param errorCode 错误码对象
     */
    public CodeException(ExceptionCodeEnum errorCode) {
        super(errorCode);
    }

    /**
     * 创建异常
     *
     * @param errorCode 错误码对象
     * @param data 附加的数据
     */
    public CodeException(ExceptionCodeEnum errorCode, Object data) {
        super(errorCode, data);
    }

    /**
     * 创建异常
     *
     * @param errorCode 错误码对象
     * @param args 模板参数值
     */
    public CodeException(ExceptionCodeEnum errorCode, Object[] args) {
        super(errorCode, args);
    }

}
