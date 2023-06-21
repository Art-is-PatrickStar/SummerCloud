package com.wsw.summercloud.task.config;

import com.wsw.summercloud.task.client.ArchiveServiceClient;
import com.wsw.summercloud.task.client.UserServiceClient;
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
    @Value("${archive.service.url}")
    private String archiveServiceUrl;
    @Value("${user.service.url}")
    private String userServiceUrl;

    @Bean("archiveServiceWebClient")
    public WebClient archiveServiceWebClient() {
        return WebClient.builder()
                .baseUrl(archiveServiceUrl)
                .build();
    }

    @Bean("userServiceWebClient")
    public WebClient userServiceWebClient() {
        return WebClient.builder()
                .baseUrl(userServiceUrl)
                .build();
    }

    @SneakyThrows
    @Bean("archiveServiceClient")
    public ArchiveServiceClient archiveServiceClient(WebClient archiveServiceWebClient) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(archiveServiceWebClient)).build();
        return httpServiceProxyFactory.createClient(ArchiveServiceClient.class);
    }

    @SneakyThrows
    @Bean("userServiceClient")
    public UserServiceClient userServiceClient(WebClient userServiceWebClient) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(userServiceWebClient)).build();
        return httpServiceProxyFactory.createClient(UserServiceClient.class);
    }
}