package com.icore.winvaz.service.user.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/7/23 14:41
 * @Version 1.0.0
 */
@ApiModel("我的二维码响应")
@Setter
@Getter
public class QrCodeResponse {
    @ApiModelProperty("二维码标识")
    private String codeMark;
    @ApiModelProperty("二维码过期时间")
    private Integer timeOut;
}
