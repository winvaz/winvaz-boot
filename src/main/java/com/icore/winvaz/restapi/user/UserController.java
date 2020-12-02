package com.icore.winvaz.restapi.user;

import com.icore.winvaz.dao.shiro.SysUserMapper;
import com.icore.winvaz.dao.shiro.SysUserRoleMapper;
import com.icore.winvaz.entity.model.shiro.SysUser;
import com.icore.winvaz.entity.model.shiro.SysUserRole;
import com.icore.winvaz.restapi.Result;
import com.icore.winvaz.service.user.UserService;
import com.icore.winvaz.service.user.response.QrCodeResponse;
import com.icore.winvaz.util.security.SHA256Util;
import com.icore.winvaz.util.shiro.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/7/23 13:39
 * @Version 1.0.0
 */
@Api(tags = "用户控制器")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 我的二维码
     *
     * @param userId
     * @return
     */
    @ApiOperation("我的二维码")
    @ApiImplicitParam(name = "userId", value = "用户id")
    @PostMapping("/qrcode-info")
    public Result qrCodeInfo(Long userId) {

        // 用户信息
        QrCodeResponse qrCodeResponse = userService.qrCodeInfo(userId);

        return Result.success(qrCodeResponse);
    }


    /**
     * @param sysUser
     * @throws
     * @Description 登录接口
     * @author wdq
     * @create 2020/8/4 09:39
     * @Return java.util.Map<java.lang.String, java.lang.Object>
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody SysUser sysUser) {
        Map<String, Object> result = new HashMap<>();
        // 进行身份验证
        try {
            // 验证身份和登录
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserName(),
                    sysUser.getPassword());
            // 验证成功进行登录操作
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            result.put("code", 500);
            result.put("msg", "用户不存在或密码错误");
        } catch (LockedAccountException e) {
            result.put("code", 500);
            result.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            result.put("code", 500);
            result.put("msg", "该用户不存在");
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "位置异常");
        }
        result.put("code", 0);
        result.put("msg", "登录成功");
        result.put("token", ShiroUtil.getSession().getId().toString());

        return result;
    }

    /**
     * 未登录
     *
     * @Author Sans
     * @CreateTime 2019/6/20 9:22
     */
    @PostMapping("/unlogin")
    public Map<String, Object> unlogin() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "您还没登录，不要瞎溜达");

        return map;
    }

    /**
     * 添加一个用户演示接口
     * 这里仅作为演示不加任何权限和重复查询校验
     *
     * @Author Sans
     * @CreateTime 2020/1/6 9:22
     */
    @RequestMapping("/testAddUser")
    public Map<String, Object> testAddUser() {
        // 设置基础参数
        SysUser sysUser = new SysUser();
        sysUser.setUserName("user1");
        sysUser.setState("NORMAL");
        // 随机生成盐值
        String salt = RandomStringUtils.randomAlphanumeric(20);
        sysUser.setSalt(salt);
        // 进行加密
        String password = "123456";
        sysUser.setPassword(SHA256Util.sha256(password, sysUser.getSalt()));
        // 保存用户
        sysUserMapper.saveOne(sysUser);
        // 保存角色
        SysUserRole sysUserRoleEntity = new SysUserRole();
        sysUserRoleEntity.setUserId(sysUser.getId()); // 保存用户完之后会把ID返回给用户实体
        sysUserRoleMapper.saveOne(sysUserRoleEntity);
        // 返回结果
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "添加成功");
        return map;
    }
}
