package com.microservices.chapter06gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Chapter06GatewayApplication

fun main(args: Array<String>) {
    runApplication<Chapter06GatewayApplication>(*args)
}

@Bean
fun routes(builder: RouteLocatorBuilder): RouteLocator {
    return builder.routes()
            .route {
                it.path("/greetings/**").uri("http://127.0.0.1:8001")
            }
            .build()
}
