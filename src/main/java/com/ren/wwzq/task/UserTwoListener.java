package com.ren.wwzq.task;

import com.ren.wwzq.models.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author Administrator
 * @Date 2020/5/18
 * @Description TODO
 */
@Slf4j
@Component
public class UserTwoListener implements SmartApplicationListener {

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> event) {
        //只有UserRegisterEvent监听类型才会执行下面逻辑
        return event == UserRegisterEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> source) {
        //只有在UserService内发布的UserRegisterEvent事件时才会执行下面逻辑
        return source == MyRegister.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //转换事件类型
        UserRegisterEvent userRegisterEvent = (UserRegisterEvent) applicationEvent;
        //获取注册用户对象信息
        User user = userRegisterEvent.getUser();
        log.info("用户信息2222:{}",user);
    }

    /**
     * return 的数值越小证明优先级越高，执行顺序越靠前。
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 2;
    }
}
