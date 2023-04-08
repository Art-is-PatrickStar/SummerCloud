package com.wsw.summercloud.archive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class SummercloudArchiveServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummercloudArchiveServiceApplication.class, args);
    }

}
