package com.microservices.chapter06gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Chapter06GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chapter06GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		System.out.println("myRoute");
		return builder.routes()
				.route(p -> p
						.path("/greetings/**")
						.uri("http://127.0.0.1:8001"))
				.build();
	}

}
