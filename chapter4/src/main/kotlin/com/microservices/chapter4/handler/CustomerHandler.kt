package com.microservices.chapter4.handler

import com.microservices.chapter4.service.CustomerService
import com.microservices.chapter4.vo.Customer
import com.microservices.chapter4.vo.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.kotlin.core.publisher.onErrorResume
import reactor.kotlin.core.publisher.toMono
import java.net.URI

@Component
class CustomerHandler(private val customerService: CustomerService) {
    fun get(serverRequest: ServerRequest) =
            customerService
                    .getCustomer(serverRequest.pathVariable("id").toInt())
                    .flatMap { ok().body(it.toMono()) }
                    .switchIfEmpty(notFound().build())

    fun create(serverRequest: ServerRequest) =
            customerService
                    .createCustomer(serverRequest.bodyToMono())
                    .flatMap {
                        created(URI.create("/customer/${it.id}")).build()
                    }.onErrorResume(Exception::class) {
                        badRequest().body(ErrorResponse("error creating customer", it.message
                                ?: "error").toMono())
                    }

    fun delete(serverRequest: ServerRequest) =
            customerService
                    .deleteCustomer(serverRequest.pathVariable("id").toInt())
                    .flatMap {
                        if (it) ok().build()
                        else status(HttpStatus.NOT_FOUND).build()
                    }

    fun search(serverRequest: ServerRequest) =
            ok().body(customerService
                    .searchCustomer(serverRequest.queryParam("nameFilter").orElse("")), Customer::class.java)

}