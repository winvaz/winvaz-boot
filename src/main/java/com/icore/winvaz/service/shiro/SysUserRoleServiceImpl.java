package com.icore.winvaz.service.shiro;

import com.icore.winvaz.dao.shiro.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/8/4 10:34
 * @Version 1.0.0
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
}
