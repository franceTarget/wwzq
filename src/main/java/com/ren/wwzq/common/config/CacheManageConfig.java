package com.ren.wwzq.common.config;


import net.sf.ehcache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.URL;

/**
 * @author: target
 * @date: 2020/5/8 15:06
 * @description:
 */
@Configuration
public class CacheManageConfig {

    @Bean
    public CacheManager getInstance(){
        URL url = getClass().getResource("/ehcache.xml");
        return CacheManager.create(url);
    }
}
