package com.microservices.charpter7

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class Charpter7Application

fun main(args: Array<String>) {
	runApplication<Charpter7Application>(*args)
}

@RestController
class GreetingsController {
	@GetMapping("/greetings")
	fun greetings() = "hello from a Docker."
}