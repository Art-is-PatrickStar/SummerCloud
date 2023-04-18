package com.wsw.summercloud.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class SummercloudTaskServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummercloudTaskServiceApplication.class, args);
    }

}
