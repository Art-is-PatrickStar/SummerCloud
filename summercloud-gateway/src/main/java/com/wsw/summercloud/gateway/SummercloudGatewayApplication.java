package com.wsw.summercloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.wsw.summercloud"})
public class SummercloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummercloudGatewayApplication.class, args);
    }

}
