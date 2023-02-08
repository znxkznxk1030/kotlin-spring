package com.microservices.chapter3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {
    @Autowired
    lateinit var customers: ConcurrentHashMap<Int, Customer>

    @RequestMapping(value = ["/customer/{id}"], method = [RequestMethod.GET])
    fun getCustomer(@PathVariable id: Int) = customers.get(id)

    @RequestMapping(value = ["/customers"], method = [RequestMethod.GET])
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
        customers.filter { it.value.name.contains(nameFilter, true) }.values.toList()
}