package com.wsw.summercloud.archive.config;

import com.wsw.summercloud.archive.client.TaskServiceClient;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/20 17:11
 */
@Configuration
public class WebClientConfig {
    @Value("${task.service.url}")
    private String taskServiceUrl;

    @Bean
    public WebClient taskServiceWebClient() {
        return WebClient.builder()
                .baseUrl(taskServiceUrl)
                .build();
    }

    @Bean
    @SneakyThrows
    public TaskServiceClient taskServiceClient(WebClient taskServiceWebClient) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(taskServiceWebClient)).build();
        return httpServiceProxyFactory.createClient(TaskServiceClient.class);
    }
}