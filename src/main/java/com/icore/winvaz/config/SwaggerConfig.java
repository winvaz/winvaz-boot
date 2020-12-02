package com.icore.winvaz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Deciption SpringBoot整合Swagger2
 * 这里提供一个配置类，首先通过 @EnableSwagger2 注解启用 Swagger2 ，然后配置一个 Docket Bean，这个 Bean 中，配置映射路径和要扫描的接口的位置，在 apiInfo 中，主要配置一下
 * Swagger2 文档网站的信息，例如网站的 title，网站的描述，联系人的信息，使用的协议等等。
 * 如此，Swagger2 就算配置成功了，非常方便。
 * 此时启动项目，输入 http://localhost:8080/swagger-ui.html ，能够看到如下页面，说明已经配
 * 置成功了
 * <p>
 * 1. @Api 注解可以用来标记当前 Controller 的功能。
 * 2. @ApiOperation 注解用来标记一个方法的作用。
 * 3. @ApiImplicitParam 注解用来描述一个参数，可以配置参数的中文含义，也可以给参数设置默认
 * 值，这样在接口测试的时候可以避免手动输入。
 * 4. 如果有多个参数，则需要使用多个 @ApiImplicitParam 注解来描述，多个 @ApiImplicitParam 注
 * 解需要放在一个 @ApiImplicitParams 注解中。
 * 5. 需要注意的是，@ApiImplicitParam 注解中虽然可以指定参数是必填的，但是却不能代替
 * @RequestParam(required = true) ，前者的必填只是在 Swagger2 框架内必填，抛弃了 Swagger2 ，这个限制就没用了，所以假如开发者需要指定一个参数必填， @RequestParam
 * (required = true) 注解还是不能省略。
 * <p>
 * 如果我们的 Spring Boot 项目中集成了 Spring Security，那么如果不做额外配置，Swagger2 文档可能
 * 会被拦截，此时只需要在 Spring Security 的配置类中重写 configure 方法，添加如下过滤即可
 * @Override public void configure(WebSecurity web) throws Exception {
 * web.ignoring()
 * .antMatchers("/swagger-ui.html")
 * .antMatchers("/v2/**")
 * .antMatchers("/swagger-resources/**");
 * }
 * 如此之后，Swagger2 文件就不需要认证就能访问了。
 * @Author wdq
 * @Create 2020/7/23 10:54
 * @Version 1.0.0
 */
@Configuration
//swagger2的配置文件，在项目的启动类的同级文件建立
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.icore.winvaz.restapi"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                ;

    }

    private List<? extends SecurityScheme> securitySchemes() {
        // 设置请求头信息
        List<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        result.add(apiKey);

        return result;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("winvaz-boot整合Swagger2")
                .description("winvaz-boot整合Swagger2，详细信息......")
                .version("1.0")
                .contact(new Contact("winvaz", "https://mail.163.com/", "winvaz@163.com"))
                .license("The Apache License")
                .licenseUrl("http://www.baidu.com")
                .build();
    }

    private List<SecurityContext> securityContexts() {
        //设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/*/.*"));

        return result;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", authorizationScopes));

        return result;
    }

}
