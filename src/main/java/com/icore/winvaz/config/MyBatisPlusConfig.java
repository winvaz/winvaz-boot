package com.icore.winvaz.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Deciption MyBatisPlus分页插件配置类，扫描注解放到启动类上了
 * @Author wdq
 * @Create 2020/8/7 10:17
 * @Version 1.0.0
 */
@Configuration
public class MyBatisPlusConfig {

    @Bean
    MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor paginationInterceptor = new MybatisPlusInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        // paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        // paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }
}
