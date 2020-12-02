package com.icore.winvaz.config;

import com.icore.winvaz.dao.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/8/7 15:08
 * @Version 1.0.0
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.icore.winvaz.dao", markerInterface = BaseMapper.class)
public class MyBatisConfig {

    @Value("${spring.datasource.url}")
    private String url;
}
