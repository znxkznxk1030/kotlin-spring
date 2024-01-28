package com.microservices.chapter06gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Chapter06GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chapter06GatewayApplication.class, args);
	}

}
