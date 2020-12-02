package com.icore.winvaz.service.impl.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icore.winvaz.constant.user.UserConstant;
import com.icore.winvaz.dao.user.UserMapper;
import com.icore.winvaz.entity.model.user.User;
import com.icore.winvaz.service.user.UserService;
import com.icore.winvaz.service.user.response.QrCodeResponse;
import com.icore.winvaz.util.exception.CodeException;
import com.icore.winvaz.util.exception.ExceptionCodeEnum;
import com.icore.winvaz.util.security.SecurityQRUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/7/23 13:51
 * @Version 1.0.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOps;

    /**
     * @param userId
     * @throws
     * @Description 我的二维码
     * @author wdq
     * @create 2020/7/23 14:43
     * @Return com.icore.winvaz.service.user.response.QrCodeResponse
     */
    @Override
    public QrCodeResponse qrCodeInfo(Long userId) {
        // 获取用户信息
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new CodeException(ExceptionCodeEnum.EX_USER_NOT_FOUND);
        }
        // 司机认证标识
        Integer driverVerifed = user.getDriverVerifed();
        if (driverVerifed != null && driverVerifed != UserConstant.Verifed.VERIFIED) {
            // 未认证
            throw new CodeException(ExceptionCodeEnum.EX_DRIVER_UNCERTIFIED);
        }

        QrCodeResponse qrCodeResponse = new QrCodeResponse();

        // 二维码
        // 加密的二维码为：邀请码+_+时间+_+ND
        String qrKey = SecurityQRUtils.createQRKey(user.getInvitationCode());
        // 二维码唯一标识加密
        // String workKey = InitializerUtils.getPlatformWorkKey(UserConstant.WorkType.QR);
        // 金额密钥
        String workKey = "cf2fe1df2a45d7fab83e4c730985105abce0d89ad3163d32947d4b094798939fe483f0bf34b192a9";

        String encrypt = SecurityQRUtils.encrypt(qrKey, workKey);

        qrCodeResponse.setCodeMark(encrypt);

        // key
        String key = UserConstant.QrCodeCache.QRCODE_MARK + ":" + user.getId();
        // 失效时间,秒为单位
        Integer limitTime = null;
        // 判断失效时间是否配置
        if (limitTime == null) {
            // 没有配置返回-1，长期有效
            qrCodeResponse.setTimeOut(-1);
            // 存储到redis，用户二维码没有失效时间
            valueOps.set(key, qrKey);
        } else {
            qrCodeResponse.setTimeOut(limitTime);
            // 存储到redis，用户二维码根据配置的失效时间失效
            valueOps.set(key, qrKey, limitTime, TimeUnit.SECONDS);
        }

        return qrCodeResponse;
    }
}
