package com.example.businessinstance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BusinessInstanceAApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessInstanceAApplication.class, args);
    }

}
