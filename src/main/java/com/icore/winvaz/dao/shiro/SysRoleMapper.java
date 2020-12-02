package com.icore.winvaz.dao.shiro;

import com.icore.winvaz.dao.BaseMapper;
import com.icore.winvaz.entity.model.shiro.SysRole;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole, Long> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectSysRoleByUserId(Long id);
}