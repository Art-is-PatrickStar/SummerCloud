package com.wsw.summercloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication(scanBasePackages = {"com.wsw.summercloud"})
public class SummercloudUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummercloudUserServiceApplication.class, args);
    }

}
