package com.icore.winvaz.javaee.servlet;

import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/6/14 15:50
 * @Version 1.0.0
 */
@SpringBootTest
public class HttpServletTest extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 发送状态码
        response.sendError(404, "您请求的资源找到了，就是不给你看！");
    }
}
