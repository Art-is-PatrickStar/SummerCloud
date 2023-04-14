//package com.wsw.summercloud.task.config.scrap;
//
//import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
//import org.apache.shardingsphere.core.yaml.config.sharding.YamlShardingRuleConfiguration;
//import org.apache.shardingsphere.core.yaml.swapper.ShardingRuleConfigurationYamlSwapper;
//import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.support.TransactionTemplate;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @Description: 数据源配置
// * @Author: wangsongwen
// * @Date: 2023/4/7 09:52
// */
//@Configuration
//public class DataSourceDefineConfig {
//    /**
//     * task服务默认数据源,采用ShardingJdbc数据源
//     *
//     * @return DataSource
//     */
//    @Bean(name = "taskDataSource")
//    public DataSource getTaskDataSource() throws SQLException {
//        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfigurationYamlSwapper().swap(getTaskShardingRules());
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//        dataSourceMap.put("ds0", getTaskRawDataSource());
//        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration, getTaskShardingProperties());
//    }
//
//    /**
//     * task服务原始数据源,定义它是为了给sharding数据源提供支持,请不要在代码中直接使用该数据源
//     *
//     * @return DataSource
//     */
//    @Bean(name = "taskRawDataSource")
//    @ConfigurationProperties(prefix = "datasource.task")
//    public DataSource getTaskRawDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    /**
//     * task服务的sharding-jdbc properties
//     *
//     * @return Properties
//     */
//    @Bean(name = "taskShardingProperties")
//    @ConfigurationProperties(prefix = "sharding-props.task")
//    public Properties getTaskShardingProperties() {
//        return new Properties();
//    }
//
//    /**
//     * task服务的sharding-jdbc rules
//     *
//     * @return YamlShardingRuleConfiguration
//     */
//    @Bean(name = "taskShardingRules")
//    @ConfigurationProperties(prefix = "sharding-rules.task")
//    public YamlShardingRuleConfiguration getTaskShardingRules() {
//        return new YamlShardingRuleConfiguration();
//    }
//
//    @Bean(name = "h2DataSource")
//    @ConfigurationProperties(prefix = "datasource.h2")
//    public DataSource h2DataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    //这里注入的是dynamic datasource
//    @Bean
//    @Primary
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Bean("h2JdbcTemplate")
//    public JdbcTemplate h2JdbcTemplate(@Qualifier("h2DataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Bean
//    @Primary
//    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean("h2TransactionManager")
//    public PlatformTransactionManager h2TransactionManager(@Qualifier("h2DataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean
//    @Primary
//    public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager) {
//        return new TransactionTemplate(platformTransactionManager);
//    }
//
//    @Bean("h2TransactionTemplate")
//    public TransactionTemplate h2TransactionTemplate(@Qualifier("h2TransactionManager") PlatformTransactionManager platformTransactionManager) {
//        return new TransactionTemplate(platformTransactionManager);
//    }
//}
