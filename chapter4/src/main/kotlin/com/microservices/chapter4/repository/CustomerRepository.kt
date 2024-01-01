package com.microservices.chapter4.repository

import com.microservices.chapter4.vo.Customer
import com.microservices.chapter4.vo.Telephone
import jakarta.annotation.PostConstruct
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Repository
class CustomerRepository(private val template: ReactiveMongoTemplate) {

    companion object {
        val initialCustomers = listOf(
                Customer(1, "Kotlin"),
                Customer(2, "Spring"),
                Customer(3, "Microservice", Telephone("+44", "7123456789"))
        )
    }

    @PostConstruct
    fun initialRepository() = initialCustomers
            .map { it.toMono() }
            .map(this::create)
            .map(Mono<Customer>::subscribe)

    fun create(customer: Mono<Customer>) = template.save(customer)
}