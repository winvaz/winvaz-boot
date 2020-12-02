package com.icore.winvaz.util.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/8/6 10:51
 * @Version 1.0.0
 */
@Data
public class MyBatisPlusGeneratorConfig {

    /**
     * table 前缀
     */
    private String tablePrefix;

    /**
     * 要生成的表名
     */
    private String[] tables;

    /**
     * 实体父类
     */
    private String entitySuperClass;

    /**
     * Mapper父类
     */
    private String mapperSuperClass;

    /**
     * 实体与父类共用字段
     */
    private String[] entityCommonColumns;

    /**
     * 数据库地址
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 是否需要给实体添加基类
     */
    private String needEntitySuperClass;

    /**
     * 数据库配置四要素
     */
    private String driverName;

    /**
     * 数据库类型
     */
    private DbType dbType;

    /**
     * 项目路径
     */
    private String projectPath;

    /**
     * 基本包名
     */
    private String basePackage;

    /**
     * 模块名
     */
    private String modelName;

    /**
     * 作者
     */
    private String authorName;
}
