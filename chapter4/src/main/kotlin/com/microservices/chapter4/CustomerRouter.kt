package com.microservices.chapter4

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.kotlin.core.publisher.toMono

@Configuration
class CustomerRouter(private val customerHandler: CustomerHandler) {

    @Bean
    fun customerRoutes(): RouterFunction<*> = router {
        GET("/customer") {
            ServerResponse.ok().body(Customer(1, "functional web").toMono(), Customer::class.java)
        }

        ("/functional").nest {
            ("/customer").nest {
                GET("/{id}", customerHandler::get)
                POST("", customerHandler::create)
            }
            "/customers".nest {
                GET("", customerHandler::search)
            }
        }
    }
}