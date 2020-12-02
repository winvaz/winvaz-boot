package com.icore.winvaz.service.shiro;

import com.icore.winvaz.dao.shiro.SysMenuMapper;
import com.icore.winvaz.entity.model.shiro.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/8/3 10:48
 * @Version 1.0.0
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> selectSysMenuByRoleId(Long id) {
        return sysMenuMapper.selectSysMenuByRoleId(id);
    }
}
