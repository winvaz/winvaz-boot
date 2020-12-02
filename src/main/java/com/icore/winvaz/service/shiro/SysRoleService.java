package com.icore.winvaz.service.shiro;

import com.icore.winvaz.entity.model.shiro.SysRole;

import java.util.List;

public interface SysRoleService {

    List<SysRole> selectSysRoleByUserId(Long id);
}