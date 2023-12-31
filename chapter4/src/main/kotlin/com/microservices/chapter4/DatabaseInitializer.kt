package com.microservices.chapter4

import com.microservices.chapter4.repository.CustomerRepository
import com.microservices.chapter4.vo.Customer
import com.microservices.chapter4.vo.Telephone
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DatabaseInitializer {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    companion object {
        val initialCustomers = listOf(
                Customer(1, "Kotlin"),
                Customer(2, "Spring"),
                Customer(3, "Microservice", Telephone("+44", "7123456789"))
        )
    }

    @PostConstruct
    fun initData() {
        customerRepository.saveAll(initialCustomers).subscribe{
            println("Default customers created")
        }
    }
}
