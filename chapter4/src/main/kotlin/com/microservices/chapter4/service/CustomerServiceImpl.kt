package com.microservices.chapter4.service

import com.microservices.chapter4.exception.CustomerExistException
import com.microservices.chapter4.repository.CustomerRepository
import com.microservices.chapter4.vo.Customer
import com.microservices.chapter4.vo.Telephone
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {

    @Autowired
    lateinit var customerRepository: CustomerRepository


    override fun getCustomer(id: Int): Mono<Customer> {
        return customerRepository.findById(id)
    }

    override fun createCustomer(customerMono: Mono<Customer>) = customerRepository.create(customerMono)
    override fun deleteCustomer(id: Int) = customerRepository.delete(id).map{ it.deletedCount > 0 }

}