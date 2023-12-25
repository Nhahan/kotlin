package com.mvc.issueservice.exception

import com.mvc.issueservice.log
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ServerException::class)
    fun handlerServerException(ex: ServerException): ErrorResponse {
        log().error { ex.message }

        return ErrorResponse(code = ex.code, message = ex.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ErrorResponse {
        log().error { ex.message }

        return ErrorResponse(code = 500, message = "Internal Server Error")
    }
}