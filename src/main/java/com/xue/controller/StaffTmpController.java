package com.xue.controller;

import com.xue.service.StaffTmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/*
 **定时任务
 */
//
@Configuration
@EnableScheduling
@Controller
public class StaffTmpController {

    @Autowired
    private StaffTmpService staffTmpService;

    @Scheduled(cron = "0 0/5 * * * ?")
    private void configureTasks(){
        System.out.println("启动任务");
        staffTmpService.saveAC();
        staffTmpService.saveLeagsoft();
        staffTmpService.saveNetdisk();
    }

}
