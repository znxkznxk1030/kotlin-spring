package com.microservices.chapter4

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController {
    @Autowired
    private lateinit var customerService: CustomerService

    @RequestMapping(value = ["/customer/{id}"], method = [RequestMethod.GET])
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Any> {
        val customer = customerService.getCustomer(id)

        return if (customer == null )
            ResponseEntity(customer, HttpStatus.OK)
        else ResponseEntity(ErrorResponse("Customer Not Found", "customer '$id' not found"), HttpStatus.NOT_FOUND)
    }

    @RequestMapping(value = ["/customers"], method = [RequestMethod.GET])
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
        customerService.searchCustomers(nameFilter)


    @RequestMapping(value = ["/customer"], method = [RequestMethod.POST])
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Unit> {
        customerService.createCustomer(customer)
        return ResponseEntity(HttpStatus.CREATED)
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