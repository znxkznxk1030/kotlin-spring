package com.microservices.chapter4.router

import com.microservices.chapter4.handler.CustomerHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router

@Configuration
class CustomerRouter(private val customerHandler: CustomerHandler) {

    @Bean
    fun customerRoutes(): RouterFunction<*> = router {
        "/customer".nest {
            GET("/{id}", customerHandler::get)
            POST("", customerHandler::create)
            DELETE("/{id}", customerHandler::delete)
        }
        "/customers".nest {
            GET("", customerHandler::search)
        }
    }
}