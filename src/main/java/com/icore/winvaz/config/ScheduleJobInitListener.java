package com.icore.winvaz.config;

import com.icore.winvaz.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Deciption 定时任务监听器
 * @Author wdq
 * @Create 2020/11/6 16:25
 * @Version 1.0.0
 */
@Component
@Order(1)
public class ScheduleJobInitListener implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
