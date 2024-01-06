package com.microservices.chapter4.service

import com.microservices.chapter4.repository.CustomerRepository
import com.microservices.chapter4.vo.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class CustomerServiceImpl : CustomerService {

    @Autowired
    lateinit var customerRepository: CustomerRepository


    override fun getCustomer(id: Int): Mono<Customer> {
        return customerRepository.findById(id)
    }

    override fun createCustomer(customerMono: Mono<Customer>) = customerRepository.create(customerMono)
    override fun deleteCustomer(id: Int) = customerRepository.delete(id).map{ it.deletedCount > 0 }
    override fun searchCustomer(nameFilter: String): Flux<Customer> = customerRepository.findCustomer(nameFilter)

}