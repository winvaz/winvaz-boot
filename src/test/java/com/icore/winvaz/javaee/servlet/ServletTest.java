package com.icore.winvaz.javaee.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/5/30 19:02
 * @Version 1.0.0
 */
// 第一种注解扫描方式，标记位Servlet，以便启动器扫描
//@WebServlet(name = "servletTest", urlPatterns = "/servletTest")
// SpringBoot测试用法
@SpringBootTest
public class ServletTest implements Servlet {

    // 模拟Http请求
    private MockMvc mockMvc;

    // 初始化工作
    @BeforeEach
    public void startUp() {
        // 独立安装测试
        mockMvc = MockMvcBuilders.standaloneSetup(new ServletTest()).build();
    }

    /**
     * 出生时调用一次
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init().....");
    }

    /**
     * 工作时调用0-N次
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException,
            IOException {
        System.out.println("service().....");
    }

    /**
     * 死亡时调用一次
     */
    @Override
    public void destroy() {
        System.out.println("destory().....");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }
}
