package com.ren.wwzq.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * @description 重写的函数决定了最后选择的DataSource
 * @author willing
 * @date 2020/10/19
 */
public class MultiRouteDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // 通过绑定线程的数据源上下文实现多数据源的动态切换
        return DataSourceContext.getRouterKey();
    }
}
