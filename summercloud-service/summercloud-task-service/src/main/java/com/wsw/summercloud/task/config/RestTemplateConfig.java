package com.wsw.summercloud.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/6 14:36
 */
@Configuration
public class RestTemplateConfig {
    @Bean("taskRestTemplate")
    public RestTemplate taskRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(30000);
        requestFactory.setReadTimeout(3000);
        return new RestTemplate(requestFactory);
    }
}
