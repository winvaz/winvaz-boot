package com.icore.winvaz.entity.model.shiro;

import com.icore.winvaz.entity.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色表
 */
@ApiModel(value = "com-icore-winvaz-entity-model-shiro-SysRole")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseModel {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
}