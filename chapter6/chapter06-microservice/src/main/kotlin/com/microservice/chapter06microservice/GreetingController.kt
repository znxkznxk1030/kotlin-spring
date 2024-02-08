package com.microservice.chapter06microservice

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {
    @Value("\${microservice.example.greetings}")
    private lateinit var greetings: String

    @GetMapping("/hello")
    fun greetings() = greetings

    @GetMapping("/hi")
    fun greeting() = greetings
}
