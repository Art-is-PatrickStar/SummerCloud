package com.wsw.summercloud.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication(scanBasePackages = {"com.wsw.summercloud"})
public class SummercloudTaskServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummercloudTaskServiceApplication.class, args);
    }

}
