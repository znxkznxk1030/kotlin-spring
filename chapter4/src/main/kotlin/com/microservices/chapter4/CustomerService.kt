package com.microservices.chapter4

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {
    fun getCustomer(id: Int): Mono<Customer>?
    fun createCustomer(customerMono: Mono<Customer>): Mono<*>
    fun deleteCustomer(id: Int)
    fun updateCustomer(id: Int, customer: Customer)
    fun searchCustomers(nameFilter: String): Flux<Customer>
}
