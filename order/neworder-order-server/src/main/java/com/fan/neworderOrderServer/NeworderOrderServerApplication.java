package com.fan.neworderOrderServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.fan.neworderUserClient.feign", "com.fan.neworderProductClient.feign"})
public class NeworderOrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeworderOrderServerApplication.class, args);
    }

}
