package com.icore.winvaz.util.exception;

/**
 * @Deciption 顶级异常，所有的自定义异常继承此类
 * @Author wdq
 * @Create 2019/12/18 15:50
 */
public class WinvazException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WinvazException(String message, Throwable cause) {
        super(message, cause);
    }

    public WinvazException(String message) {
        super(message);
    }

    public WinvazException(Throwable cause) {
        super(cause);
    }

    public WinvazException() {
        super();
    }

    public WinvazException(
            String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }

}
