package com.ren.wwzq.task;

import com.ren.wwzq.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author Administrator
 * @Date 2020/5/18
 * @Description TODO
 */
@Component
public class MyRegister {
    @Autowired
    private ApplicationContext applicationContext;

    public void register(User user){
        //发布UserRegisterEvent事件
        applicationContext.publishEvent(new UserRegisterEvent(this,user));
    }

}
