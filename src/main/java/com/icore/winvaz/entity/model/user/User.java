package com.icore.winvaz.entity.model.user;

import com.icore.winvaz.entity.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Deciption 用户对象
 * @Author wdq
 * @Create 2020/7/23 11:46
 * @Version 1.0.0
 */
//添加Swagger
@ApiModel
@Setter
@Getter
public class User extends BaseModel {

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("个性签名")
    private String signature;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("性别（1男 2女）")
    private Integer sex;
    @ApiModelProperty("出生日期")
    private Date birthday;
    @ApiModelProperty("注册方式")
    private Integer registerWay;
    @ApiModelProperty("注册时间")
    private Date registerTime;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("手机号开通登录标志")
    private Boolean mobileLogin;
    @ApiModelProperty("手机号开通登录时间")
    private Date mobileLoginTime;
    @ApiModelProperty("邮箱地址")
    private String email;
    @ApiModelProperty("邮箱开通登录标志")
    private Boolean emailLogin;
    @ApiModelProperty("邮箱开通登录时间")
    private Date emailLoginTime;
    @ApiModelProperty("省份编号")
    private Integer provinceCode;
    @ApiModelProperty("城市编号")
    private Integer cityCode;
    @ApiModelProperty("区县编号")
    private Long districtCode;
    @ApiModelProperty("所在的详细地址")
    private String address;
    @ApiModelProperty("注册渠道（0手机端IOS，1手机端android，2网站，3微信公众号）")
    private Integer channel;
    @ApiModelProperty("用户状态（0正常 1锁定 2注销）")
    private Integer state;
    @ApiModelProperty("用户状态变更时间")
    private Date stateChangeTime;
    @ApiModelProperty("邀请码")
    private String invitationCode;
    @ApiModelProperty("真实姓名")
    private String name;
    @ApiModelProperty("身份证号")
    private String identityNo;
    @ApiModelProperty("身份认证标识（0未认证、1认证中、2认证成功 3认证失败）")
    private Integer certVerifed;
    @ApiModelProperty("驾照认证标识（0未认证、1认证中、2认证成功 3认证失败）")
    private Integer driverVerifed;
    @ApiModelProperty("账户id")
    private Long accId;
    @ApiModelProperty("账户名")
    private String accNo;
    @ApiModelProperty("司机角色（0无 1普通司机 2金牛司机 3兜底司机 4车队司机）")
    private Integer driverRole;
    @ApiModelProperty("押金缴纳标识（0未缴纳 1已缴纳 4已退还）")
    private Integer depositFlag;
    @ApiModelProperty("是否拥有企业用户身份（true是 false否）")
    private Boolean enterpriseUser;
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("用户角色（0普通用户 1企业用户 2车队用户）")
    private Integer userRole;
    @ApiModelProperty("企业信息表id（用户角色为1时有值）")
    private Long enterpriseId;
    @ApiModelProperty("账号（0可用 1不可用）")
    private Integer canUse;
    @ApiModelProperty("货车车队id（用户角色为2时有值）")
    private Long fleetId;

}