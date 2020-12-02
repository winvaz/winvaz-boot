package com.icore.winvaz.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.icore.winvaz.entity.model.user.User;
import com.icore.winvaz.service.user.response.QrCodeResponse;
import org.springframework.validation.annotation.Validated;

/**
 * @Deciption 用户Service
 * @Author wdq
 * @Create 2020/7/23 13:51
 * @Version 1.0.0
 */
@Validated
public interface UserService extends IService<User> {

    /**
     * @Description 我的二维码
     * @author wdq
     * @create 2020/7/23 14:43
     * @param userId
     * @Return com.icore.winvaz.service.user.response.QrCodeResponse
     * @exception
     */
    QrCodeResponse qrCodeInfo(Long userId);
}
