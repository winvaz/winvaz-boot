package com.icore.winvaz.service.shiro;

import com.icore.winvaz.dao.shiro.SysRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/8/4 10:33
 * @Version 1.0.0
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
}
