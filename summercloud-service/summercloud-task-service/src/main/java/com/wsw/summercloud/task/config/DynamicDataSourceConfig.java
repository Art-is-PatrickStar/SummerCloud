package com.wsw.summercloud.task.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.provider.AbstractDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Description: dynamic-datasource-spring-boot-starter多数据源配置
 * @Author: wangsongwen
 * @Date: 2023/4/7 09:50
 */
@Configuration
public class DynamicDataSourceConfig {
    private final DynamicDataSourceProperties properties;
    private final Map<String, DataSource> dataSources;

    public DynamicDataSourceConfig(DynamicDataSourceProperties properties, @Lazy Map<String, DataSource> dataSources) {
        this.properties = properties;
        this.dataSources = dataSources;
    }

    /**
     * 把所有数据源加入dynamic-datasource-spring-boot-starter管理
     *
     * @return DynamicDataSourceProvider
     */
    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider() {
        return new AbstractDataSourceProvider() {
            @Override
            public Map<String, DataSource> loadDataSources() {
                return dataSources;
            }
        };
    }

    /**
     * 设置主数据源为DynamicRoutingDataSource
     *
     * @return DataSource
     */
    @Bean
    @Primary
    public DataSource dataSource() {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setPrimary(properties.getPrimary());
        dataSource.setStrict(properties.getStrict());
        dataSource.setStrategy(properties.getStrategy());
        dataSource.setP6spy(properties.getP6spy());
        dataSource.setSeata(properties.getSeata());
        return dataSource;
    }
}
