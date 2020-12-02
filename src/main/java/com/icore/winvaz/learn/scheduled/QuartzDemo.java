package com.icore.winvaz.learn.scheduled;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Deciption Quartz定时任务框架Demo
 * Quartz在使用过程中，有两个关键概念，一个是JobDetail(要做的事情)，另一个是触发器(什么时 候做)，要定义JobDetail，需要先定义 Job，Job 的定义有两种方式
 * 1.第一种方式，直接定义一个Bean(1.先将Job注册到Spring容器中。2.这种方式有缺陷，无法传参)
 * 2.第二种方式，继承QuartzJobBean并实现默认方法。
 * @Author wdq
 * @Create 2020/7/22 14:54
 * @Version 1.0.0
 */
public class QuartzDemo {

    QuartzJobDetailDemo quartzJobDetailDemo;

    public void setHelloTask(QuartzJobDetailDemo quartzJobDetailDemo) {
        this.quartzJobDetailDemo = quartzJobDetailDemo;
    }

    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        quartzJobDetailDemo.sayHello();
    }
}