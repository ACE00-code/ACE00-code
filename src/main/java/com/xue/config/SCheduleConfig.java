package com.xue.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/*
 **定时任务
 */

@Configuration
@EnableScheduling
public class SCheduleConfig {

    @Scheduled(cron = "0 0 0 * * ?")
    private void configureTasks(){
        System.out.println("hello");
    }

}