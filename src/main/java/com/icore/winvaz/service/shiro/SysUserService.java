package com.icore.winvaz.service.shiro;

import com.icore.winvaz.entity.model.shiro.SysUser;

/**
 * @author wdq
 * @Description TODO
 * @create 2020/8/4 10:51
 */
public interface SysUserService {

    SysUser selectUserByName(String username);
}
