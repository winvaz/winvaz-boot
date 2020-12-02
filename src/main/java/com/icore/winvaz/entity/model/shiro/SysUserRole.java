package com.icore.winvaz.entity.model.shiro;

import com.icore.winvaz.entity.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
    * 用户与角色关系表
    */
@ApiModel(value="com-icore-winvaz-entity-model-shiro-SysUserRole")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserRole extends BaseModel {
    /**
    * ID
    */
    @ApiModelProperty(value="ID")
    private Long id;

    /**
    * 用户ID
    */
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
    * 角色ID
    */
    @ApiModelProperty(value="角色ID")
    private Long roleId;
}