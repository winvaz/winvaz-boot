package com.icore.winvaz.learn.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Deciption 条件注解
 * @Author wdq
 * @Create 2020/7/21 20:22
 * @Version 1.0.0
 */
public class ConditionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().getSystemProperties().put("people", "南方人");
        ctx.register(FoodConfig.class);
        ctx.refresh();

        Food food = (Food) ctx.getBean("food");
        System.out.println(food.showName()); // 米饭
    }
}

/**
 * 定义一个接口
 */
interface Food {
    String showName();
}

/**
 * 接口的实现类
 */
class Rice implements Food {
    @Override
    public String showName() {
        return "米饭";
    }
}

class Noodles implements Food {

    @Override
    public String showName() {
        return "面条";
    }
}

/**
 * 两实现类的条件类
 * 在 matches 方法中做条件属性判断，当系统属性中的 people 属性值为 '北方人' 的时候，
 * NoodlesCondition 的条件得到满足，当系统中 people 属性值为 '南方人' 的时候，
 * RiceCondition 的条 件得到满足，换句话说，哪个条件得到满足，一会就会创建哪个 Bean 。
 */
class RiceCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            return context.getEnvironment().getProperty("people").equals("南方人");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

class NoodlesCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            return context.getEnvironment().getProperty("people").equals("北方人");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

/**
 * 两实现类的配置类
 * 两个 Bean 的名字都为 food，这不是巧合，而是有意取的。两个 Bean 的返回值都为其父类对象 Food。
 * 每个 Bean 上都多了 @Conditional 注解，当 @Conditional 注解中配置的条件类的 matches 方法 返回值为 true 时，对应的 Bean 就会生效。
 */
@Configuration
class FoodConfig {

    @Bean("food")
    @Conditional(RiceCondition.class)
    Food rice() {
        return new Rice();
    }

    @Bean("food")
    @Conditional(NoodlesCondition.class)
    Food noodles() {
        return new Noodles();
    }
}