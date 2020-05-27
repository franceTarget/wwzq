package com.ren.wwzq.task;


import com.ren.wwzq.models.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @author: target
 * @date: 2020/5/18 19:51
 * @description:
 */
@Slf4j
//@Component
//@EnableScheduling
public class AysncTaskTimer extends ApplicationObjectSupport implements SchedulingConfigurer {

    @Value("${aysnc.task.cron}")
    private String cron;

    public ApplicationContext context;

    @Autowired
    private MyRegister register;

    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {
        super.initApplicationContext(context);
        this.context = context;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            //发布任务
            publishEvent();
        }, triggerContext -> {
            // 任务触发，可修改任务的执行周期
            CronTrigger trigger = new CronTrigger(cron);
            Date nextExecutionTime = trigger.nextExecutionTime(triggerContext);
            return nextExecutionTime;
        });
    }

    private void publishEvent() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("好了");
        log.info("开始注册:{}", user);
        register.register(user);
    }

}
