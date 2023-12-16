package com.microservices.chapter4

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class CustomerController {
    @Autowired
    private lateinit var customerService: CustomerService

    @RequestMapping(value = ["/customer/{id}"], method = [RequestMethod.GET])
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Mono<Customer>> {
        val customer = customerService.getCustomer(id)
        return ResponseEntity(customer, HttpStatus.OK)
    }

    @RequestMapping(value = ["/customers"], method = [RequestMethod.GET])
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
        customerService.searchCustomers(nameFilter)


    @RequestMapping(value = ["/customer"], method = [RequestMethod.POST])
    fun createCustomer(@RequestBody customerMono: Mono<Customer>): ResponseEntity<Mono<*>> {
        return ResponseEntity(customerService.createCustomer(customerMono), HttpStatus.CREATED)
    }

    @RequestMapping(value = ["/customer/{id}"], method = [RequestMethod.DELETE])
    fun deleteCustomer(@PathVariable id: Int): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.deleteCustomer(id)
            status = HttpStatus.OK
        }
        return ResponseEntity(status)
    }

    @RequestMapping(value = ["/customer/{id}"], method = [RequestMethod.PUT])
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND

        if (customerService.getCustomer(id) != null) {
            customerService.updateCustomer(id, customer)
            status = HttpStatus.ACCEPTED
        }

        return ResponseEntity(status)
    }
}