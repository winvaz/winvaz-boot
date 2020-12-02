package com.icore.winvaz.config;

import com.icore.winvaz.learn.scheduled.QuartzDemo;
import com.icore.winvaz.learn.scheduled.QuartzJobDetailDemo;
import com.icore.winvaz.util.web.Authorized;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.security.core.parameters.P;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * 配置JobDetail和Trigger触发器
 * 1. JobDetail 的配置有两种方式:MethodInvokingJobDetailFactoryBean 和 JobDetailFactoryBean 。
 * 2. 使用 MethodInvokingJobDetailFactoryBean 可以配置目标 Bean 的名字和目标方法的名字，这种 方式不支持传参。
 * 3. 使用 JobDetailFactoryBean 可以配置 JobDetail ，任务类继承自 QuartzJobBean ，这种方式支持 传参，将参数封装在 JobDataMap 中进行传递。
 * 4. Trigger 是指触发器，Quartz 中定义了多个触发器，这里向大家展示其中两种的用法， SimpleTrigger 和 CronTrigger 。
 * 5. SimpleTrigger 有点类似于前面说的 @Scheduled 的基本用法。
 * 6. CronTrigger 则有点类似于 @Scheduled 中 cron 表达式的用法。
 *
 * @author wdq
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private JobFactory jobFactory;

    @Bean
    MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {

        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("QuartzJobDetailDemo");
        bean.setTargetBeanName("sayHello");

        return bean;
    }

    @Bean
    JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        // bean.setJobClass((Class<? extends Job>) QuartzDemo.class);

        JobDataMap map = new JobDataMap();
        map.put("quartzJobDetailDemo", quartzJobDetailDemo());
        bean.setJobDataMap(map);

        return bean;
    }

    @Bean
    SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setStartTime(new Date());
        bean.setRepeatCount(5);
        bean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        bean.setRepeatInterval(3000);

        return bean;
    }

    @Bean
    CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setCronExpression("0/10 * * * * ?");
        bean.setJobDetail(jobDetailFactoryBean().getObject());

        return bean;
    }

    /**
     * 调度工厂，核心类
     *
     * @param
     * @throws
     * @author wdq
     * @create 2020/11/6 16:14
     * @Return org.springframework.scheduling.quartz.SchedulerFactoryBean
     */
    @Bean
    SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        try {
            // 延迟10秒
            // schedulerFactoryBean.setStartupDelay(10);
            schedulerFactoryBean.setOverwriteExistingJobs(true);
            schedulerFactoryBean.setQuartzProperties(quartzProperties());
            schedulerFactoryBean.setJobFactory(jobFactory);
            schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
            // schedulerFactoryBean.setTriggers(cronTriggerFactoryBean().getObject());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return schedulerFactoryBean;
    }

    /**
     * 指定quartz.properties，可在配置文件中配置相关属性
     * @author wdq
     * @create 2020/11/6 16:14
     * @param
     * @Return java.util.Properties
     * @exception
     */
    @Bean
    Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/config/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();

        return propertiesFactoryBean.getObject();
    }

    /**
     * 创建sceduler
     * @author wdq
     * @create 2020/11/6 16:21
     * @param
     * @Return org.quartz.Scheduler
     * @exception
     */
    @Bean(name = "scheduler")
    Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }


    @Bean
    QuartzJobDetailDemo quartzJobDetailDemo() {
        return new QuartzJobDetailDemo();
    }
}