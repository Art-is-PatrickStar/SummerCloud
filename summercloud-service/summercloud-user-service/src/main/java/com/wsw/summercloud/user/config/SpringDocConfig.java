package com.wsw.summercloud.user.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/23 0:05
 */
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI restOpenApi() {
        return new OpenAPI().info(new Info()
                .title("summercloud-user-service")
                .description("用户服务")
                .version("1.0.0"));
    }
}