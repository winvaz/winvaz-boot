package com.icore.winvaz.service.shiro;

import com.icore.winvaz.dao.shiro.SysRoleMapper;
import com.icore.winvaz.entity.model.shiro.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/7/31 17:58
 * @Version 1.0.0
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public List<SysRole> selectSysRoleByUserId(Long id) {
        return sysRoleMapper.selectSysRoleByUserId(id);
    }
}
