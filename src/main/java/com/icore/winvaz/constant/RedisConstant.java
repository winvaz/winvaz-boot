package com.icore.winvaz.constant;

/**
 * @Deciption redis常量类
 * @Author wdq
 * @Create 2020/7/31 17:04
 * @Version 1.0.0
 */
public interface RedisConstant {

    /**
     * TOKEN前缀
     */
    String REDIS_PREFIX_LOGIN = "token_%s";
    /**
     * 过期时间2小时
     */
    Integer REDIS_EXPIRE_TWO = 7200;
    /**
     * 过期时间15分
     */
    Integer REDIS_EXPIRE_EMAIL = 900;
    /**
     * 过期时间5分钟
     */
    Integer REDIS_EXPIRE_KAPTCHA = 300;
    /**
     * 暂无过期时间
     */
    Integer REDIS_EXPIRE_NULL = -1;
}