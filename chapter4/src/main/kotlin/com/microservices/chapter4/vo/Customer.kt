package com.microservices.chapter4.vo

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import org.springframework.data.mongodb.core.mapping.Document

@JsonInclude(Include.NON_NULL)
@Document(collection = "Customers")
data class Customer(var id: Int = 0, var name: String, var telephone: Telephone? = null)
