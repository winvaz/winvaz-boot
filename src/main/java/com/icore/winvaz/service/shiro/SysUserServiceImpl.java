package com.icore.winvaz.service.shiro;

import com.icore.winvaz.dao.shiro.SysUserMapper;
import com.icore.winvaz.entity.model.shiro.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/8/3 11:05
 * @Version 1.0.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public SysUser selectUserByName(String username) {
        return sysUserMapper.selectUserByName(username);
    }
}
