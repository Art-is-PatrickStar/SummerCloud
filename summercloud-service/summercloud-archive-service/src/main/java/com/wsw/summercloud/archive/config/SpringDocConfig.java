package com.wsw.summercloud.archive.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/6 22:52
 */
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI restOpenApi() {
        return new OpenAPI().info(new Info()
                .title("summercloud-archive-service")
                .description("归档服务")
                .version("1.0.0"));
    }
}