package com.microservice.chapter06configserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableConfigServer
class Chapter06ConfigserverApplication {
    fun main(args: Array<String>) {
        runApplication<Chapter06ConfigserverApplication>(*args)
    }
}