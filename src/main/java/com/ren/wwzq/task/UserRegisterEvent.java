package com.ren.wwzq.task;

import com.ren.wwzq.models.entity.User;
import org.springframework.context.ApplicationEvent;

/**
 * @Author Administrator
 * @Date 2020/5/18
 * @Description TODO
 */
public class UserRegisterEvent extends ApplicationEvent {

    public User user;

    /**
     * @param source 发生事件的对象
     * @param user   注册用户对象
     */
    public UserRegisterEvent(Object source, User user) {
        super(source);
        this.user = user;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
