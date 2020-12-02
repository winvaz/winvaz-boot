package com.icore.winvaz.restapi.shiro;

import com.icore.winvaz.util.shiro.ShiroUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Deciption 角色控制类
 * @Author wdq
 * @Create 2020/8/4 17:01
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    /**
     * 管理员角色测试接口
     *
     * @Author Sans
     * @CreateTime 2019/6/19 10:38
     * @Return Map<String, Object> 返回结果
     */
    @GetMapping("/getAdminInfo")
    @RequiresRoles("admin")
    public Map<String, Object> getAdminInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "这里是只有管理员角色能访问的接口");
        return map;
    }

    /**
     * 用户角色测试接口
     *
     * @Author Sans
     * @CreateTime 2019/6/19 10:38
     * @Return Map<String, Object> 返回结果
     */
    @GetMapping("/getUserInfo")
    @RequiresRoles("user")
    public Map<String, Object> getUserInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "这里是只有用户角色能访问的接口");
        return map;
    }

    /**
     * 角色测试接口
     *
     * @Author Sans
     * @CreateTime 2019/6/19 10:38
     * @Return Map<String, Object> 返回结果
     */
    @GetMapping("/getRoleInfo")
    @RequiresRoles(value = {"admin", "user"}, logical = Logical.OR)
    @RequiresUser
    public Map<String, Object> getRoleInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "这里是只要有ADMIN或者USER角色能访问的接口");
        return map;
    }

    /**
     * 登出
     *
     * @Author Sans
     * @CreateTime 2019/6/19 10:38
     * @Return Map<String, Object> 返回结果
     */
    @GetMapping("/getLogout")
    @RequiresUser
    public Map<String, Object> getLogout() {
        //登出Shiro会帮我们清理掉Session和Cache
        ShiroUtil.logout();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "登出");
        return map;
    }
}
