package com.icore.winvaz.dao.shiro;

import com.icore.winvaz.dao.BaseMapper;
import com.icore.winvaz.entity.model.shiro.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser, Long> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectUserByName(String username);
}