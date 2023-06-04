package com.wsw.summercloud.archive.config;

import com.wsw.summercloud.common.properties.ThreadPoolProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/4 16:17
 */
@Configuration
public class ThreadPoolConfig {
    @Bean("archiveThreadPool")
    public ThreadPoolExecutor archiveThreadPoolExecutor() {
        ThreadPoolProperties properties = getThreadPoolProperties();
        return new ThreadPoolExecutor(
                properties.getCorePoolSize(),
                properties.getMaximumPoolSize(),
                properties.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(properties.getQueueCapacity()),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    @Bean
    @ConfigurationProperties(prefix = "archive.threadpool.config")
    public ThreadPoolProperties getThreadPoolProperties() {
        return new ThreadPoolProperties();
    }
}
