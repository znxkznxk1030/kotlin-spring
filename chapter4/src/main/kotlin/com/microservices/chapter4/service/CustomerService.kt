package com.microservices.chapter4.service

import com.microservices.chapter4.vo.Customer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {
    fun getCustomer(id: Int): Mono<Customer>
    fun createCustomer(customerMono: Mono<Customer>): Mono<Customer>
    fun searchCustomers(nameFilter: String): Flux<Customer>
}
