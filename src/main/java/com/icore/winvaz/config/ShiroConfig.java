package com.icore.winvaz.config;

import com.icore.winvaz.util.shiro.ShiroSessionIdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Deciption Shiro配置类
 * @Author wdq
 * @Create 2020/7/31 15:19
 * @Version 1.0.0
 */
@Configuration
public class ShiroConfig {

    private final String CACHE_KEY = "shiro:cache:";
    private final String SESSION_KEY = "shiro:session:";

    //Redis配置
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;

    /**
     * @param securityManager
     * @throws
     * @Description 开启Shiro-aop注解支持
     * @Attention 使用代理方式所以需要开启代码支持
     * @author wdq
     * @create 2020/8/4 09:31
     * @Return org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     */
    /*
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    */

    /**
     * @param securityManager
     * @throws
     * @Description Shiro基础配置
     * @author wdq
     * @create 2020/8/4 09:31
     * @Return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     */
    /*
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactory(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 注意过滤器配置顺序不能颠倒
        // 配置过滤:不会被拦截的链接
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/user/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        // 配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/user/unlogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    */

    /**
     * @param
     * @throws
     * @Description 安全管理器
     * @author wdq
     * @create 2020/8/4 09:31
     * @Return org.apache.shiro.mgt.SecurityManager
     */
    /*
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义Cache实现
        // 自定义Ssession管理
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(new RedisCacheManager());
        // 自定义Realm验证
        securityManager.setRealm(userRealm());
        return securityManager;
    }
    */

    /**
     * @param
     * @throws
     * @Description Session管理器
     * @author wdq
     * @create 2020/7/31 15:46
     * @Return org.apache.shiro.session.mgt.SessionManager
     */
    /*
    @Bean
    public SessionManager sessionManager() {
        // 会话管理
        ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
        // Session存入Redis
        shiroSessionManager.setSessionDAO(redisSessionDao());

        return shiroSessionManager;
    }
    */

    /**
     * 配置RedisSessionDAO
     * 使用的是shiro-redis开源插件
     *
     * @param
     * @throws
     * @author wdq
     * @create 2020/7/31 16:35
     * @Return org.apache.shiro.session.mgt.eis.SessionDAO
     */
    /*
    @Bean
    public SessionDAO redisSessionDao() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        redisSessionDAO.setKeyPrefix(SESSION_KEY);
        redisSessionDAO.setExpire(timeout);

        return redisSessionDAO;
    }
    */

    /*
    @Bean
    public IRedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setTimeout(timeout);

        return redisManager;
    }
    */

    /**
     * SessionID生成器
     *
     * @Author Sans
     * @CreateTime 2019/6/12 13:12
     */
    @Bean
    public ShiroSessionIdGenerator sessionIdGenerator() {
        return new ShiroSessionIdGenerator();
    }

    /**
     * @param
     * @throws
     * @Description 缓存管理器
     * @author wdq
     * @create 2020/8/4 16:10
     * @Return org.apache.shiro.cache.CacheManager
     */
    /*
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setKeyPrefix(CACHE_KEY);
        // 配置缓存的话要求放在session里面的实体类必须有个id标识
        redisCacheManager.setPrincipalIdFieldName("userId");

        return redisCacheManager;
    }
    */

    /**
     * @param
     * @throws
     * @Description UserRealm
     * @author wdq
     * @create 2020/8/4 16:10
     * @Return org.apache.shiro.realm.Realm
     */
    /*
    @Bean
    public Realm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());

        return userRealm;
    }
    */

    /**
     * 凭证匹配器
     * 将密码校验交给Shiro的SimpleAuthenticationInfo进行处理,在这里做匹配配置
     *
     * @Author Sans
     * @CreateTime 2019/6/12 10:48
     */
    /*
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用SHA256算法;
        shaCredentialsMatcher.setHashAlgorithmName(SHA256Util.HASH_ALGORITHM_NAME);
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        shaCredentialsMatcher.setHashIterations(SHA256Util.HASH_ITERATIONS);
        return shaCredentialsMatcher;
    }
    */
}
