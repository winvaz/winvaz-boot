package com.icore.winvaz.service.shiro;

import com.icore.winvaz.entity.model.shiro.SysMenu;

import java.util.List;

public interface SysMenuService {

    /**
     * @param id
     * @throws
     * @Description 通过角色id查询菜单
     * @author wdq
     * @create 2020/8/3 10:52
     * @Return java.util.List<com.icore.winvaz.entity.model.shiro.SysMenu>
     */
    List<SysMenu> selectSysMenuByRoleId(Long id);
}
