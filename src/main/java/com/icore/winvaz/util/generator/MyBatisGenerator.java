package com.icore.winvaz.util.generator;

import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/8/6 09:40
 * @Version 1.0.0
 */
public class MyBatisGenerator {

    public static void main(String[] args) throws Exception {
        // MBG 执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        // 当生成的代码重复时，覆盖原代码
        boolean overwrite = true;
        // 读取MBG配置文件
        InputStream inputStream = MyBatisGenerator.class.getResourceAsStream("/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(inputStream);
        inputStream.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        // 创建MBG
        org.mybatis.generator.api.MyBatisGenerator myBatisGenerator =
                new org.mybatis.generator.api.MyBatisGenerator(config, callback, warnings);
        // 执行生成代码
        myBatisGenerator.generate(null);
        // 输出警告信息
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}