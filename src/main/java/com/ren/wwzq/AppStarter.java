package com.ren.wwzq;

import com.ren.wwzq.common.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;


@Import(DynamicDataSourceRegister.class)
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.ren")
@MapperScan("com.ren.wwzq.dao")
@EnableScheduling
@EnableAsync
public class AppStarter {

    private static ApplicationContext context = null;

    public static void main(String[] args) {
        context = SpringApplication.run(AppStarter.class, args);
    }

    public static <T> T getBean(Class<T> t) {
        return context.getBean(t);
    }
}
