package com.icore.winvaz.constant.user;

/**
 * @Deciption 用户常量
 * @Author wdq
 * @Create 2020/7/23 14:45
 * @Version 1.0.0
 */
public interface UserConstant {
    /**
     * 认证标识
     *
     * @author gaigeshen
     * @since 05/16 2017
     */
    interface Verifed {
        int NOT = 0;// 0未认证
        int VERIFYING = 1;// 1认证中
        int VERIFIED = 2;// 2认证成功
        int FAILED = 3;// 3认证失败
    }

    /**
     * 二维码状态--存入缓存(Redis)时使用
     */
    interface QrCodeCache {
        //redisKey用于获取二维码标识
        String QRCODE_MARK = "qrcode.mark";
        // 每个key，30s失效
        //int SECOND_LOSS = 30;
    }
}
