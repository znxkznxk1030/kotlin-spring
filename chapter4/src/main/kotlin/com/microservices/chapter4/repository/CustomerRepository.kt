package com.microservices.chapter4.repository

import com.microservices.chapter4.vo.Customer
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CustomerRepository: ReactiveCrudRepository<Customer, Int> {
}