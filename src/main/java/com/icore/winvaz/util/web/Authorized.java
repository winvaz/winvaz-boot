package com.icore.winvaz.util.web;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Deciption 控制器方法需要处于登录状态才能访问，如果使用了此注解的话
 * @Author wdq
 * @Create 2019/12/25 15:16
 * @Version 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authorized {
}
