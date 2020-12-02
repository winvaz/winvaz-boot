package com.icore.winvaz.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icore.winvaz.util.common.Hump2Underline;
import com.icore.winvaz.util.common.LogUtils;
import com.icore.winvaz.util.exception.ExceptionCodeEnum;

/**
 * @Deciption 响应结果
 * @Author wdq
 * @Create 2019/12/18 15:45
 */
public class Result<T> {

    // jackson转换工具
    private static final ObjectMapper objectMapper =
            new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

    //debug模式
    private static boolean isDebug = true;

    /**
     * 响应码
     */
    private int code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    /**
     * 私有构造(无参）
     */
    private Result() {
    }

    /**
     * 私有构造(三参）
     */
    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * @param
     * @throws
     * @Description 成功
     * @author wdq
     * @create 2020/8/5 22:49
     * @Return com.icore.winvaz.restapi.Result
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * @param data
     * @throws
     * @Description 成功
     * @author wdq
     * @create 2020/8/5 22:49
     * @Return com.icore.winvaz.restapi.Result<T>
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result(0, null, data);
        if (isDebug) {
            String jsonString = null;
            try {
                jsonString = objectMapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jsonString = Hump2Underline.humpToUnderline(jsonString);
            LogUtils.info("出参body:{}", jsonString);
        }
        return result;
    }

    /**
     * @param code
     * @throws
     * @Description 失败
     * @author wdq
     * @create 2020/8/5 22:50
     * @Return com.icore.winvaz.restapi.Result
     */
    public static <T> Result<T> failure(int code) {
        return failure(code, null);
    }

    /**
     * @param code
     * @param message
     * @throws
     * @Description 失败
     * @author wdq
     * @create 2020/8/5 22:50
     * @Return com.icore.winvaz.restapi.Result
     */
    public static <T> Result<T> failure(int code, String message) {
        return failure(code, message, null);
    }

    /**
     * @param code
     * @param message
     * @param data
     * @throws
     * @Description 失败
     * @author wdq
     * @create 2020/8/5 22:50
     * @Return com.icore.winvaz.restapi.Result
     */
    public static <T> Result<T> failure(int code, String message, T data) {
        Result<T> result = new Result(code, message, data);
        if (isDebug) {
            String jsonString = null;
            try {
                jsonString = objectMapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            LogUtils.error("出参body:" + jsonString);
        }
        return result;
    }

    /**
     * 未授权返回结果
     */
    public static <T> Result<T> forbidden(T data) {
        return new Result<>(ExceptionCodeEnum.EX_PERMISSION_DENIED.code(), null, data);
    }

    /**
     * 未登录返回结果
     */
    public static <T> Result<T> unauthorized(T data) {
        return new Result<>(ExceptionCodeEnum.UNAUTHORIZED.code(), null, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}