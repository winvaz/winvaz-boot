package com.icore.winvaz.serurity.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @Deciption 动态权限相关业务类
 * @Author wdq
 * @Create 2020/8/10 16:40
 * @Version 1.0.0
 */
public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
