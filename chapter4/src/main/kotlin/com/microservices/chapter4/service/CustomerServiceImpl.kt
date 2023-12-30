package com.microservices.chapter4.service

import com.microservices.chapter4.exception.CustomerExistException
import com.microservices.chapter4.vo.Customer
import com.microservices.chapter4.vo.Telephone
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap
@Component
class CustomerServiceImpl : CustomerService {
    companion object {
        val initialCustomers = arrayOf(Customer(1, "Kotlin", Telephone("+44", "7123456789")),
                Customer(2, "Spring"),
                Customer(3, "MicroService", Telephone("+44", "7123456789"))
        )
    }

    val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int) = customers[id]?.toMono() ?: Mono.empty()

    override fun createCustomer(customerMono: Mono<Customer>) =
            customerMono.flatMap {
                if(customers[it.id] == null) {
                    customers[it.id] = it
                    it.toMono()
                } else {
                    Mono.error(CustomerExistException("Customer ${it.id} already exist."))
                }
            }

    override fun searchCustomers(nameFilter: String): Flux<Customer> {
        return customers.filter { it.value.name.contains(nameFilter) }.map(Map.Entry<Int, Customer>::value).toFlux()
    }
}