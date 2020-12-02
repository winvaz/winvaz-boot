package com.icore.winvaz.serurity.component;

import cn.hutool.core.util.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @Deciption 动态权限数据源，用于获取动态权限规则
 * @Author wdq
 * @Create 2020/8/10 16:44
 * @Version 1.0.0
 */
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, ConfigAttribute> configAttributeMap = null;

    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    @PostConstruct
    public void loadDataSource() {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }

    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (Objects.isNull(configAttributeMap)) {
            this.loadDataSource();
        }
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        // 获取当前访问路径
        String url = ((FilterInvocation) object).getRequestUrl();
        String path = URLUtil.getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();
        Set<Map.Entry<String, ConfigAttribute>> set = configAttributeMap.entrySet();
        Iterator<Map.Entry<String, ConfigAttribute>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ConfigAttribute> entry = iterator.next();
            String key = entry.getKey();
            if (pathMatcher.match(key, path)) {
                configAttributes.add(configAttributeMap.get(key));
            }
        }
        // 未设置操作权限，返回空集合
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
