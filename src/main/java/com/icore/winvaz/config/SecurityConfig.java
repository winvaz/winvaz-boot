package com.icore.winvaz.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icore.winvaz.restapi.Result;
import com.icore.winvaz.serurity.component.DynamicAccessDecisionManager;
import com.icore.winvaz.serurity.component.DynamicSecurityFilter;
import com.icore.winvaz.serurity.component.DynamicSecurityMetadataSource;
import com.icore.winvaz.serurity.component.DynamicSecurityService;
import com.icore.winvaz.serurity.component.JWTAuthenticationTokenFilter;
import com.icore.winvaz.serurity.component.RestAuthenticationEntryPointHandler;
import com.icore.winvaz.serurity.component.RestfulAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;

/**
 * SpringSecurity配置类
 *
 * @Author wdq
 * @Create 2020/7/27 20:32
 * @Version 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启权限注解,默认是关闭的
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;

    /**
     * 加密方式
     *
     * @author wdq
     * @create 2020/8/7 16:42
     * @Return org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
     */
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置登录验证逻辑
     *
     * @param auth
     * @author wdq
     * @create 2020/8/7 16:57
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 这里可启用我们自己的登陆验证逻辑
        // auth.authenticationProvider(userAuthenticationProvider);

        // 下面这两行配置表示在内存中配置了两个用户
        /*
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("winvaz").roles("admin").password(bCryptPasswordEncoder().encode("123"));
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("zhangsan").roles("user").password(bCryptPasswordEncoder().encode("123"));
        */

        auth.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        return new JWTAuthenticationTokenFilter(authenticationManager());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig() {
        return new IgnoreUrlsConfig();
    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }

    @Bean
    public RestAuthenticationEntryPointHandler restAuthenticationEntryPointHandler() {
        return new RestAuthenticationEntryPointHandler();
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityFilter dynamicSecurityFilter() {
        return new DynamicSecurityFilter();
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicAccessDecisionManager dynamicAccessDecisionManager() {
        return new DynamicAccessDecisionManager();
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityMetadataSource dynamicSecurityMetadataSource() {
        return new DynamicSecurityMetadataSource();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http.authorizeRequests();
        // 不需要保护的资源路径允许访问
        for (String url : ignoreUrlsConfig().getUrls()) {
            registry.antMatchers(url).permitAll();
        }
        // 允许跨域请求的OPTIONS请求
        registry.antMatchers(HttpMethod.OPTIONS).permitAll();
        // 任何请求需要身份验证
        registry.and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                // 开启跨域
                .and()
                .cors()
                // 关闭跨站请求防护及不使用session
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 自定义权限拒绝处理类
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler())
                .authenticationEntryPoint(restAuthenticationEntryPointHandler())
                // 自定义权限拦截器JWT过滤器
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 有动态权限配置时添加动态权限校验过滤器
        if (Objects.nonNull(dynamicSecurityService)) {
            registry.and().addFilterBefore(dynamicSecurityFilter(), FilterSecurityInterceptor.class);
        }
    }


    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType("application/json:charset=utf-8");
            PrintWriter out = response.getWriter();
            Result result = Result.success("登录成功!");
            out.write(new ObjectMapper().writeValueAsString(result));
            out.flush();
            out.close();
        });
        filter.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setContentType("application/json:charset=utf-8");
            PrintWriter out = response.getWriter();
            Result result = Result.failure(500, "登录失败!");
            out.write(new ObjectMapper().writeValueAsString(result));
            out.flush();
            out.close();
        });
        filter.setAuthenticationManager(authenticationManagerBean());

        return filter;
    }

    /**
     * 内部类
     */
    class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
            if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
                ObjectMapper mapper = new ObjectMapper();
                UsernamePasswordAuthenticationToken authRequest = null;
                try (InputStream in = request.getInputStream()) {
                    Map<String, String> authenticationBean = mapper.readValue(in, Map.class);
                    authRequest = new UsernamePasswordAuthenticationToken(authenticationBean.get("username"),
                            authenticationBean.get("password"));
                } catch (Exception e) {
                    e.printStackTrace();
                    authRequest = new UsernamePasswordAuthenticationToken("", "");
                } finally {
                    setDetails(request, authRequest);
                    return this.getAuthenticationManager().authenticate(authRequest);
                }
            } else {
                return super.attemptAuthentication(request, response);
            }
        }
    }
}