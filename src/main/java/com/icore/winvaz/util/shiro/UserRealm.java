package com.icore.winvaz.util.shiro;

import com.icore.winvaz.entity.model.shiro.SysMenu;
import com.icore.winvaz.entity.model.shiro.SysRole;
import com.icore.winvaz.entity.model.shiro.SysUser;
import com.icore.winvaz.service.shiro.SysMenuService;
import com.icore.winvaz.service.shiro.SysRoleService;
import com.icore.winvaz.service.shiro.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @Deciption Shiro权限匹配和账号密码匹配
 * @Author wdq
 * @Create 2020/7/31 17:13
 * @Version 1.0.0
 */
public class UserRealm extends AuthorizingRealm {

    /**
     * 超过最大错误次数锁定账户
     */
    private static final int MAX_FAIL_COUNT = 5;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 授权权限
     * 用户进行权限验证时候Shiro会去缓存中找,如果查不到数据,会执行这个方法去查权限,并放入缓存中
     *
     * @param principalCollection
     * @throws
     * @author wdq
     * @create 2020/7/31 17:14
     * @Return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取用户id
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        // 这里进行授权和处理
        Set<String> roleSet = new HashSet<>();
        Set<String> permSet = new HashSet<>();
        // 查询该用户所具有的角色
        List<SysRole> sysRoleEntityList = sysRoleService.selectSysRoleByUserId(sysUser.getId());
        for (SysRole role : sysRoleEntityList) {
            roleSet.add(role.getRoleName());
            List<SysMenu> sysMenuList = sysMenuService.selectSysMenuByRoleId(role.getId());
            for (SysMenu menu : sysMenuList) {
                permSet.add(menu.getPerms());
            }
        }
        // 将查到的权限和角色分别传入authorizationInfo
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(permSet);

        return authorizationInfo;
    }

    /**
     * @Description 用户认证 Dock
     * @author wdq
     * @create 2020/8/3 10:58
     * @param authenticationToken
     * @Return org.apache.shiro.authc.AuthenticationInfo
     * @exception
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户的输入账号
        String username = (String) authenticationToken.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到进行验证
        //实际项目中,这里可以根据实际情况做缓存,如果不做,Shiro自己也是有时间间隔机制,2分钟内不会重复执行该方法
        SysUser user = sysUserService.selectUserByName(username);
        // 判断账号是否存在
        if (Objects.isNull(user)) {
            throw new AuthenticationException();
        }
        // 判断账号是否可用
        if (Objects.isNull(user.getState()) || Objects.equals("PROHIBIT", user.getState())) {
            throw new LockedAccountException();
        }

        // 验证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName()
        );
        // 验证成功开始踢人(清除缓存和session)
        ShiroUtil.deleteCache(username, true);

        return authenticationInfo;
    }
}
