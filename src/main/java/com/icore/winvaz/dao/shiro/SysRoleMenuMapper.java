package com.icore.winvaz.dao.shiro;

import com.icore.winvaz.dao.BaseMapper;
import com.icore.winvaz.entity.model.shiro.SysRoleMenu;

public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu, Long> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);
}