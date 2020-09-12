package com.ren.wwzq.common.datasource;

import com.ren.wwzq.common.datasource.hikari.DataSourceManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description
 * @Author wings
 * @Date 2020/9/12
 */
@Configuration
public class RefreshableDataSourceConfiguration {

    @Bean
    public DynamicDataSource dataSource(DataSourceManager dataSourceManager) {
        DataSource actualDataSource = dataSourceManager.createDataSource();
        return new DynamicDataSource(actualDataSource);
    }
}
