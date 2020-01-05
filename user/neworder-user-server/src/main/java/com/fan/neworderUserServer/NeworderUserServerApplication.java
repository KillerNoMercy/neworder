package com.fan.neworderUserServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NeworderUserServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeworderUserServerApplication.class, args);
	}

}
