package com.wsw.summercloud.task.config;

import com.wsw.summercloud.task.aop.ApiLogAspect;
import com.wsw.summercloud.task.service.impl.ApiLogService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/8 13:18
 */
@Configuration
@ConditionalOnProperty(value = "task.api-log.enabled")
public class ApiLogConfiguration {
    @Bean
    public ApiLogAspect getApiLogAspect() {
        return new ApiLogAspect();
    }

    @Bean
    public ApiLogService getApiLogService(ApiLogProperties apiLogProperties) {
        return new ApiLogService(apiLogProperties);
    }

    @Bean
    @ConfigurationProperties("task.api-log")
    public ApiLogProperties getApiLogProperties() {
        return new ApiLogProperties();
    }
}
