package com.wsw.summercloud.task.config;

import cn.hutool.core.collection.CollectionUtil;
import com.wsw.summercloud.task.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/28 15:44
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Value("#{'${not.authentication.urls}'.empty ? null : '${not.authentication.urls}'.split(',')}")
    private List<String> notAuthenticationUrls;

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        if (CollectionUtil.isEmpty(notAuthenticationUrls)) {
            registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");
        } else {
            registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**").excludePathPatterns(notAuthenticationUrls);
        }
    }
}