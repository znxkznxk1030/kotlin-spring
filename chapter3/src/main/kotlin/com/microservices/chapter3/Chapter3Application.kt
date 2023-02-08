package com.microservices.chapter3

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class Chapter3Application {
    companion object {
        val initialCustomer = arrayOf(
            Customer(1, "kotlin"),
            Customer(2, "spring"),
            Customer(3, "Microservice")
        )
    }

    @Bean
    fun customers() = ConcurrentHashMap(initialCustomer.associateBy(Customer::id))
}

fun main(args: Array<String>) {
    runApplication<Chapter3Application>(*args)
}
