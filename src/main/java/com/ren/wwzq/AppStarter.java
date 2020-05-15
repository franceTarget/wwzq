package com.ren.wwzq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.ren")
@MapperScan("com.ren.wwzq.dao")
public class AppStarter {

    private static ApplicationContext context = null;

    public static void main(String[] args) {
        context = SpringApplication.run(AppStarter.class, args);
    }

    public static <T> T getBean(Class<T> t) {
        return context.getBean(t);
    }
}
