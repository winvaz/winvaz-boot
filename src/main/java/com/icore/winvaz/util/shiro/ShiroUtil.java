package com.icore.winvaz.util.shiro;

import com.icore.winvaz.config.BeansRegistry;
import com.icore.winvaz.entity.model.shiro.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;

import java.util.Collection;
import java.util.Objects;

/**
 * @Deciption Shiro 工具类
 * @Author wdq
 * @Create 2020/8/3 18:05
 * @Version 1.0.0
 */
public class ShiroUtil {

    /**
     * 私有构造
     */
    private ShiroUtil() {

    }

    private static RedisSessionDAO redisSessionDAO = BeansRegistry.getBean(RedisSessionDAO.class);

    /**
     * @param
     * @throws
     * @Description 获取当前用户Session
     * @author wdq
     * @create 2020/8/3 18:08
     * @Return org.apache.shiro.session.Session
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * @param
     * @throws
     * @Description 用户登出
     * @author wdq
     * @create 2020/8/3 18:09
     * @Return void
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * @param
     * @throws
     * @Description 获取当前用户信息
     * @author wdq
     * @create 2020/8/3 18:10
     * @Return com.icore.winvaz.entity.model.shiro.SysUser
     */
    public static SysUser getUserInfo() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * @param username
     * @param isRemoveSession
     * @throws
     * @Description 删除用户缓存信息
     * @author wdq
     * @create 2020/8/3 18:11
     * @Return void
     */
    public static void deleteCache(String username, boolean isRemoveSession) {
        // 从缓存中获取session
        Session session = null;
        Object attribute = null;
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        for (Session sessionInfo : sessions) {
            // 遍历sessions,找到该用户名称对应的session
            attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (Objects.isNull(attribute)) {
                continue;
            }
            SysUser sysUser = (SysUser) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
            if (Objects.isNull(sysUser)) {
                continue;
            }
            if (Objects.equals(username, sysUser.getUserName())) {
                session = sessionInfo;
                break;
            }
        }
        if (Objects.isNull(session) || Objects.isNull(attribute)) {
            return;
        }
        // 删除session
        if (isRemoveSession) {
            redisSessionDAO.delete(session);
        }
        // 删除Cache，在访问受限接口时会重新授权
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        Authenticator authenticator = securityManager.getAuthenticator();
        ((LogoutAware) authenticator).onLogout((SimplePrincipalCollection) attribute);
    }
}
