package com.icore.winvaz.learn.scheduled;

import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @Deciption 定时任务Demo
 * 1. 首先使用 @Scheduled 注解开启一个定时任务。
 * 2. fixedRate 表示任务执行之间的时间间隔，具体是指两次任务的开始时间间隔，即第二次任务开始 时，第一次任务可能还没结束。
 * 3. fixedDelay 表示任务执行之间的时间间隔，具体是指本次任务结束到下次任务开始之间的时间间 隔。
 * 4. initialDelay 表示首次任务启动的延迟时间。
 * 5. 所有时间的单位都是毫秒。
 * @Scheduled 注解也支持cron表达式，使用cron表达式，可以非常丰富的描述定时任务的时间。cron 表达式格式如下:
 * [秒] [分] [小时] [日] [月] [周] [年]
 * ? 表示不指定值，即不关心某个字段的取值时使用。需要注意的是，月份中的日期和星期可能会 起冲突，因此在配置时这两个得有一个是 ?
 * * 表示所有值，例如:在秒的字段上设置 * ,表示每一秒都会触发
 * , 用来分开多个值，例如在周字段上设置 "MON,WED,FRI" 表示周一，周三和周五触发
 * - 表示区间，例如在秒上设置 "10-12",表示 10,11,12秒都会触发
 * / 用于递增触发，如在秒上面设置"5/15" 表示从5秒开始，每增15秒触发(5,20,35,50)
 * ## 序号(表示每月的第几个周几)，例如在周字段上设置"6##3"表示在每月的第三个周六，(用 在
 * 母亲节和父亲节再合适不过了) 周字段的设置，若使用英文字母是不区分大小写的 ，即 MON 与mon相同
 * L 表示最后的意思。在日字段设置上，表示当月的最后一天(依据当前月份，如果是二月还会自动 判断是否是润年), 在周字段上表示星期六，相当于"7"或"SAT"(注意周日算是第一天)。如果
 * 在"L"前加上数字，则表示该数据的最后一个。例如在周字段上设置"6L"这样的格式,则表示"本月最 后一个星期五"
 * W 表示离指定日期的最近工作日(周一至周五)，例如在日字段上设置"15W"，表示离每月15号最近 的那个工作日触发。如果15号正好是周六，则找最近的周五(14号)触发, 如果15号是周未，则找最 近的下周一(16号)
 * 触发，如果15号正好在工作日(周一至周五)，则就在该天触发。如果指定格式为 "1W",它则表示每月1号往后最近的工作日触发。如果1号正是周六，则将在3号下周一触发。 (注，"W"前只能设置具体的数字,不允许区间"-")
 * L 和 W 可以一组合使用。如果在日字段上设置"LW",则表示在本月的最后一个工作日触发(一般指 发工资 )
 * @Author wdq
 * @Create 2020/7/22 14:15
 * @Version 1.0.0
 */
// @Component
public class ScheduledDemo {

    @Scheduled(fixedRate = 2000)
    public void fixedRate() {
        System.out.println("fixedRate>>>" + LocalDateTime.now());
    }

    @Scheduled(fixedDelay = 2000)
    public void fixedDelay() {
        System.out.println("fixedDelay>>>" + LocalDateTime.now());
    }

    @Scheduled(initialDelay = 2000, fixedDelay = 2000)
    public void initialDelay() {
        System.out.println("initialDelay>>>" + LocalDateTime.now());
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void cron() {
        System.out.println(LocalDateTime.now());
    }
}
