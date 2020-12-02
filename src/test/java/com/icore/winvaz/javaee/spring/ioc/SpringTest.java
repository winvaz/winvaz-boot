package com.icore.winvaz.javaee.spring.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @Deciption Spring基础测试
 * @Author wdq
 * @Create 2020/6/30 17:44
 * @Version 1.0.0
 */
public class SpringTest {
    public static void main(String[] args) {
        // src目录下创建Spring核心配置文件:applicationContext.xml
        /**
         * <beans xmlns="http://www.springframework.org/schema/beans"
         *              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         *              xsi:schemaLocation="http://www.springframework.org/schema/beans
         *              http://www.springframework.org/schema/beans/spring-beans.xsd">
         * 	</beans>
         */
        // 加载Spring核心工厂对象ApplicationContext(应用上下文)
        // 应用程序从Spring环境中获取自己运行的资源
        // 1.从类路径下(src)加载Spring运行环境（推荐）
        // ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.从应用路径(工程)加载Spring运行环境
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("applicationContext.xml");
        // 2.从上下文对象中获取你要的资源
        // Account accout = (Account) applicationContext.getBean("accout");
        // System.out.println(accout.getId());

        // 加载Spring核心工厂BeanFactory接口
        Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        // Account account = (Account) beanFactory.getBean("account");
        // System.out.println(account.getId());

        /**
         * 问题：BeanFactory与ApplicationContext的区别
         * 加载方式
         * BeanFactory：延迟加载，使用bean时才进行初始化
         * ApplicationContext：加载配置文件时，初始化bean对象
         * 功能
         * ApplicationContext提供更多的功能
         * 国际化处理
         * 事件传递
         * Bean自动装配
         * 各种不同应用层的Context实现
         * 实际开发中，优先选择ApplicationContext对象
         */
    }
}
