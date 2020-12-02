package com.icore.winvaz.entity.model.shiro;

import com.icore.winvaz.entity.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户表
 */
@ApiModel(value = "com-icore-winvaz-entity-model-shiro-SysUser")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseModel {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 盐值
     */
    @ApiModelProperty(value = "盐值")
    private String salt;

    /**
     * 状态:NORMAL正常  PROHIBIT禁用
     */
    @ApiModelProperty(value = "状态:NORMAL正常  PROHIBIT禁用")
    private String state;
}