package com.ren.wwzq.common.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author Administrator
 * @Date 2020/5/18
 * @Description TODO
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig implements AsyncConfigurer {
    /**
     * 获取异步线程池执行对象
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        //使用Spring内置线程池任务对象
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        taskExecutor.setCorePoolSize(5);
        //最大线程数
        taskExecutor.setMaxPoolSize(10);
        //线程最大空闲时间
        taskExecutor.setKeepAliveSeconds(60);
        //队列大小
        taskExecutor.setQueueCapacity(50);
        //线程名称前缀
        taskExecutor.setThreadNamePrefix("my-pool-");
        /**
         * 拒绝策略
         * AbortPolicy，直接抛出RejectedExecutionException
         * CallerRunsPolicy，直接在主线程中执行
         * DiscardOldestPolicy 抛弃队列头的任务，然后重试execute。
         * DiscardPolicy，直接丢弃
         */
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

//    @Override
//    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//        return null;
//    }
}
