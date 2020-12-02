package com.icore.winvaz.entity.model.shiro;

import com.icore.winvaz.entity.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
    * 角色与权限关系表
    */
@ApiModel(value="com-icore-winvaz-entity-model-shiro-SysRoleMenu")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenu extends BaseModel {
    /**
    * ID
    */
    @ApiModelProperty(value="ID")
    private Long id;

    /**
    * 角色ID
    */
    @ApiModelProperty(value="角色ID")
    private Long roleId;

    /**
    * 权限ID
    */
    @ApiModelProperty(value="权限ID")
    private Long menuId;
}