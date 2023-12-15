package com.microservices.chapter3

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ErrorHandler {
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun jsonParseExceptionHandler(servletRequest: HttpServletRequest, exception: Exception)
            : ResponseEntity<ErrorResponse> {
        println(exception)
        return ResponseEntity(ErrorResponse("JSON Error", exception.message
                ?: "invalid json"), HttpStatus.BAD_REQUEST)
    }
}
