package com.icore.winvaz.dao.shiro;

import com.icore.winvaz.dao.BaseMapper;
import com.icore.winvaz.entity.model.shiro.SysUserRole;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole, Long> {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}