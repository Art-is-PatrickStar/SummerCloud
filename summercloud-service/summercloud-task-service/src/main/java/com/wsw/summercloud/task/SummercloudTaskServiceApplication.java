package com.wsw.summercloud.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication(scanBasePackages = {"com.wsw"}, exclude = {DataSourceAutoConfiguration.class})
public class SummercloudTaskServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummercloudTaskServiceApplication.class, args);
    }

}
