package com.icore.winvaz.dao.shiro;

import com.icore.winvaz.dao.BaseMapper;
import com.icore.winvaz.entity.model.shiro.SysMenu;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu, Long> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> selectSysMenuByRoleId(Long id);
}