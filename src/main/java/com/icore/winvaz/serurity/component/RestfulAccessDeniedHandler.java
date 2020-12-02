package com.icore.winvaz.serurity.component;

import cn.hutool.json.JSONUtil;
import com.icore.winvaz.restapi.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Deciption 自定义返回结果：没有权限访问时
 * @Author wdq
 * @Create 2020/8/10 16:05
 * @Version 1.0.0
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(Result.forbidden(accessDeniedException.getMessage())));
        response.getWriter().flush();
    }
}
