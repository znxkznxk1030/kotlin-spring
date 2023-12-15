package com.microservices.chapter3

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include

@JsonInclude(Include.NON_NULL)
data class Customer(var id: Int = 0, var name: String = "", var telephone: Telephone? = null)
