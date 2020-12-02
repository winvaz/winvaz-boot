package com.icore.winvaz.restapi.shiro;

import com.icore.winvaz.dao.shiro.SysMenuMapper;
import com.icore.winvaz.dao.shiro.SysRoleMapper;
import com.icore.winvaz.dao.shiro.SysRoleMenuMapper;
import com.icore.winvaz.dao.shiro.SysUserMapper;
import com.icore.winvaz.entity.model.shiro.SysMenu;
import com.icore.winvaz.entity.model.shiro.SysRole;
import com.icore.winvaz.entity.model.shiro.SysRoleMenu;
import com.icore.winvaz.entity.model.shiro.SysUser;
import com.icore.winvaz.util.shiro.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Deciption 菜单控制类
 * @Author wdq
 * @Create 2020/8/4 16:58
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 获取用户信息集合
     *
     * @Author Sans
     * @CreateTime 2019/6/19 10:36
     * @Return Map<String, Object> 返回结果
     */
    @GetMapping("/getUserInfoList")
    @RequiresPermissions("sys:user:info")
    public Map<String, Object> getUserInfoList() {
        Map<String, Object> map = new HashMap<>();
        List<SysUser> sysUserEntityList = sysUserMapper.find(null);
        map.put("sysUserEntityList", sysUserEntityList);
        return map;
    }

    /**
     * 获取角色信息集合
     *
     * @Author Sans
     * @CreateTime 2019/6/19 10:37
     * @Return Map<String, Object> 返回结果
     */
    @GetMapping("/getRoleInfoList")
    @RequiresPermissions("sys:role:info")
    public Map<String, Object> getRoleInfoList() {
        Map<String, Object> map = new HashMap<>();
        List<SysRole> sysRoleEntityList = sysRoleMapper.find(null);
        map.put("sysRoleEntityList", sysRoleEntityList);
        return map;
    }

    /**
     * 获取权限信息集合
     *
     * @Author Sans
     * @CreateTime 2019/6/19 10:38
     * @Return Map<String, Object>
     */
    @GetMapping("/getMenuInfoList")
    @RequiresPermissions("sys:menu:info")
    public Map<String, Object> getMenuInfoList() {
        Map<String, Object> map = new HashMap<>();
        List<SysMenu> sysMenuEntityList = sysMenuMapper.find(null);
        map.put("sysMenuEntityList", sysMenuEntityList);
        return map;
    }

    /**
     * 获取所有数据
     *
     * @Author Sans
     * @CreateTime 2019/6/19 10:38
     * @Return Map<String, Object>
     */
    @GetMapping("/getInfoAll")
    @RequiresPermissions("sys:info:all")
    public Map<String, Object> getInfoAll() {
        Map<String, Object> map = new HashMap<>();
        List<SysUser> sysUserEntityList = sysUserMapper.find(null);
        map.put("sysUserEntityList", sysUserEntityList);
        List<SysRole> sysRoleEntityList = sysRoleMapper.find(null);
        map.put("sysRoleEntityList", sysRoleEntityList);
        List<SysMenu> sysMenuEntityList = sysMenuMapper.find(null);
        map.put("sysMenuEntityList", sysMenuEntityList);
        return map;
    }

    /**
     * 添加管理员角色权限(测试动态权限更新)
     *
     * @Author Sans
     * @CreateTime 2019/6/19 10:39
     * @Param username 用户ID
     * @Return Map<String, Object>
     */
    @PostMapping("/addMenu")
    public Map<String, Object> addMenu() {
        //添加管理员角色权限
        SysRoleMenu sysRoleMenuEntity = new SysRoleMenu();
        sysRoleMenuEntity.setMenuId(4L);
        sysRoleMenuEntity.setRoleId(1L);
        sysRoleMenuMapper.saveOne(sysRoleMenuEntity);
        //清除缓存
        String username = "admin";
        ShiroUtil.deleteCache(username, false);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "权限添加成功");
        return map;
    }
}
