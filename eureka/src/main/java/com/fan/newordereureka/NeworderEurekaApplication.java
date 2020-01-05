package com.fan.newordereureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NeworderEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeworderEurekaApplication.class, args);
    }

}
