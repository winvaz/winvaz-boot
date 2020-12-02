package com.icore.winvaz.restapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/7/20 10:08
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/login")
    public Result login() {
        return Result.failure(500, "尚未登录，请登录");
    }

    @GetMapping("/https")
    public String https(HttpServletRequest request) {
        System.out.println(request.getContextPath());
        return "Hello https !";
    }

    /**
     * 测试Spring Session
     */
    @Value("${server.port}")
    Integer port;

    @GetMapping("/set")
    public String set(HttpSession session) {
        session.setAttribute("user", "winvaz");
        return String.valueOf(port);
    }

    @GetMapping("/get")
    public String get(HttpSession session) {
        return session.getAttribute("user") + ":" + port;
    }

    // 核心技术
}