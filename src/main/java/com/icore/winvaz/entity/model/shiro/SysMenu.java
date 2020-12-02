package com.icore.winvaz.entity.model.shiro;

import com.icore.winvaz.entity.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限表
 */
@ApiModel(value = "com-icore-winvaz-entity-model-shiro-SysMenu")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends BaseModel {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String name;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String perms;
}