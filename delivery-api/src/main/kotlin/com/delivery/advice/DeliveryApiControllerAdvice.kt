package com.delivery.advice

import com.delivery.apibase.DeliveryApiExceptionResponse
import com.delivery.apibase.DeliveryApiResponseCode
import com.delivery.exception.DuplicateOrderException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class DeliveryApiControllerAdvice {

    @ExceptionHandler(value = [
        NullPointerException::class,
        IllegalArgumentException::class,
        DuplicateOrderException::class
    ])
    fun handleApiRequestException(ex: Exception): ResponseEntity<Any?>? {
        val apiException = ex.message?.let {
            DeliveryApiExceptionResponse(
                code = DeliveryApiResponseCode.INTERNAL_SERVER_ERROR.code,
                message = it,
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
            )
        }

        return ResponseEntity<Any?>(apiException, HttpStatus.BAD_REQUEST)
    }
}