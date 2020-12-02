package com.icore.winvaz.util.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/8/6 15:43
 * @Version 1.0.0
 */
public class MyBatisPlusGenerator {

    /**
     * table 前缀
     */
    private static final String DEFAULT_TABLE_PREFIX = "";

    /**
     * 实体父类
     */
    private static final String DEFAULT_ENTITY_SUPER_CLASS = "com.icore.winvaz.entity.BaseModel";

    /**
     * Mapper父类
     */
    private static final String DEFAULT_MAPPER_SUPER_CLASS = "com.icore.winvaz.dao.BaseMapper";

    /**
     * 数据源
     */
    private static final String drivername = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC&useSSL=false&" +
            "characterEncoding=utf-8";
    private static final String username = "root";
    private static final String password = "12345678";

    /**
     * 作者
     */
    private static final String DEFAULT_AUTHOR = "MyBatisPlusGenerator";

    /**
     * 模块名
     */
    private static String MODEL_NAME = "";

    /**
     * 配置
     */
    private MyBatisPlusGeneratorConfig config;

    /**
     * 生成工具类
     */
    private AutoGenerator autoGenerator;

    public MyBatisPlusGenerator(MyBatisPlusGeneratorConfig config) {
        autoGenerator = new AutoGenerator();


        initDefaultConfig(config);

        settingGeneratorConfig();
    }

    /**
     * @param
     * @throws
     * @Description 初始化默认配置
     * @author wdq
     * @create 2020/8/6 11:24
     * @Return void
     */
    private void initDefaultConfig(MyBatisPlusGeneratorConfig config) {
        this.config = new MyBatisPlusGeneratorConfig();

        String[] tables = config.getTables();
        String projectPath = config.getProjectPath();
        String basePackage = config.getBasePackage();

        Objects.requireNonNull(tables);
        Objects.requireNonNull(projectPath);
        Objects.requireNonNull(basePackage);

        this.config.setTables(tables);
        this.config.setProjectPath(projectPath);
        this.config.setBasePackage(basePackage);
        this.config.setModelName(config.getModelName());

        this.config.setTablePrefix(DEFAULT_TABLE_PREFIX);

        this.config.setDriverName(drivername);
        this.config.setUrl(url);
        this.config.setUsername(username);
        this.config.setPassword(password);

        this.config.setEntitySuperClass(DEFAULT_ENTITY_SUPER_CLASS);
        this.config.setMapperSuperClass(DEFAULT_MAPPER_SUPER_CLASS);

        this.config.setAuthorName(DEFAULT_AUTHOR);

    }

    public void settingGeneratorConfig() {
        /**
         * 数据库设置
         */
        autoGenerator.setDataSource(new DataSourceConfig()
                .setDriverName(config.getDriverName())
                .setUrl(config.getUrl())
                .setUsername(config.getUsername())
                .setPassword(config.getPassword()));

        /**
         * 全局配置
         */
        autoGenerator.setGlobalConfig(new GlobalConfig()
                // 输出目录
                .setOutputDir(config.getProjectPath() + "/src/main/java")
                // 是否覆盖文件
                .setFileOverride(true)
                // 是否开启swagger2模式
                .setSwagger2(true)
                // BaseResultMap
                .setBaseResultMap(true)
                // baseColumnList
                .setBaseColumnList(true)
                // 是否开启Open
                .setOpen(false)
                // author
                .setAuthor(config.getAuthorName())
                // mapperName
                .setMapperName("%sMapper")
                // xmlName
                .setXmlName("%sMapper")
                // serviceName
                .setServiceName("%sService")
                // serviceImplName
                .setServiceImplName("%sServiceImpl")
                // controllerName
                .setControllerName("%sController")
        );

        /**
         * 策略配置
         */
        autoGenerator.setStrategy(new StrategyConfig()
                // 此处可以设置为您的表前缀
                .setTablePrefix(config.getModelName() + "_")
                // naming 数据库表映射到实体的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                //
                .setColumnNaming(NamingStrategy.underline_to_camel)
                // include 需要包含的表名
                .setInclude(config.getTables())
                // 写入父类中的公共字段
                .setSuperEntityColumns("id")
                // superEntityClass 自定义继承的Entity类全称，带包名
                .setSuperEntityClass(config.getEntitySuperClass())
                // mapperSuperClass
                .setSuperMapperClass(config.getMapperSuperClass())
                // entityLombokModel 是否为lombok模型
                .setEntityLombokModel(true)
                // 生成 @RestController 控制器
                .setRestControllerStyle(true)
                // 驼峰转连字符
                .setControllerMappingHyphenStyle(true)
                // Boolean类型字段是否移除is前缀（默认 false）
                .setEntityBooleanColumnRemoveIsPrefix(true)
        );

        /**
         * 包配置
         */
        autoGenerator.setPackageInfo(new PackageConfig()
                // parent 自定义包路径
                .setParent(config.getBasePackage())
                // 模块名
                .setModuleName(config.getModelName())
                // entity 包名
                .setEntity("entity.model")
                // service 包名
                .setService("service")
                // serviceImpl
                .setServiceImpl("service.impl")
                // mapper 包名
                .setMapper("dao")
                // xml
                .setXml("mapper")
                // controller
                .setController("restapi")
        );

        /**
         * 注入自定义配置
         */
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };

        // 如果模板引擎是 freemarker
        // String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        List<FileOutConfig> fileOutDirList = new ArrayList();
        fileOutDirList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return config.getProjectPath() + "/src/main/resources/com/icore/winvaz/dao/"
                        + config.getModelName() + "/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });

        injectionConfig.setFileOutConfigList(fileOutDirList);

        autoGenerator.setCfg(injectionConfig);

        /**
         * 模板配置
         */
        autoGenerator.setTemplate(new TemplateConfig()
                .setXml(null)
                // .setController("templates/controller.java.vm")
        );
    }

    /**
     * @param tip
     * @throws
     * @Description 读取控制台内容
     * @author wdq
     * @create 2020/8/6 21:54
     * @Return java.lang.String
     */
    private String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + help + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String next = scanner.next();
            if (StringUtils.isNotEmpty(next)) {
                MODEL_NAME = next;
                return next;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "! ");

    }

    public void execute() {
        autoGenerator.execute();
    }

    public static void main(String[] args) {
        // 获取当前主项目在硬盘上的路径:"/Users/用户名/.../主项目名"
        // 需要注意的是，若是一个主项目内部还有子项目，在子项目中使用该语句得到的是父项目的路径
        // /Users/wdq/Documents/IntelliJWorkspace/winvaz-boot
        // String projectPath = System.getProperty("user.dir");
        String projectPath = MyBatisPlusGenerator.class.getResource("/").getPath().replace("/target/classes", "");

        // 获取当前项目在硬盘上的路径:"/Users/用户名/.../主项目名/子项目名/.../classes"
        // /Users/wdq/Documents/IntelliJWorkspace/winvaz-boot/build/classes/java/main/
        // System.out.println(Class.class.getClass().getResource("/").getPath());

        // 配置文件
        MyBatisPlusGeneratorConfig config = new MyBatisPlusGeneratorConfig();
        // 表名
        config.setTables(new String[]{"student"});
        // 项目路径
        config.setProjectPath(projectPath);
        // 包路径
        // config.setBasePackage("com.icore.winvaz");
        config.setBasePackage(MyBatisPlusGenerator.class.getPackage().getName());
        // 模块名
        config.setModelName("generator");
        // 代码生成器
        MyBatisPlusGenerator generator = new MyBatisPlusGenerator(config);
        generator.execute();
    }
}
