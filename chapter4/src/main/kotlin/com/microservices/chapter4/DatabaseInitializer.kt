package com.microservices.chapter4

import com.microservices.chapter4.repository.CustomerRepository
import com.microservices.chapter4.vo.Customer
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component

@Component
class DatabaseInitializer {
    @Autowired
    lateinit var mongoOperations: ReactiveMongoOperations

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @PostConstruct
    fun initData() {
        mongoOperations
                .collectionExists("Customers")
                .subscribe {
                    if (it != true) {
                        mongoOperations
                                .createCollection("Customers")
                                .subscribe {
                                    println("Customers collections created")
                                }
                    } else {
                        println("Customers collections already exist")
                    }

                    customerRepository.save(Customer(1, "spring")).subscribe{
                        println("Default customers created")
                    }
                }
    }
}
