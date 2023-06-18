package com.wsw.summercloud.archive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.wsw.summercloud"})
public class SummercloudArchiveServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummercloudArchiveServiceApplication.class, args);
    }

}
