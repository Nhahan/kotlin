package com.auth.exception

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.mono
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Configuration
class GlobalExceptionHandler(
    private val objectMapper: ObjectMapper
): ErrorWebExceptionHandler {
    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        return mono {
            val errorResponse = if (ex is ServerException) {
                ErrorResponse(ex.code, ex.message)
            } else {
                ErrorResponse(500, "Internal Server Error")
            }

            with(exchange.response) {
                statusCode = HttpStatus.OK
                headers.contentType = MediaType.APPLICATION_JSON

                val dataBuffer = bufferFactory().wrap(objectMapper.writeValueAsBytes(errorResponse))
                writeWith(dataBuffer.toMono()).awaitSingle()
            }
        }
    }
}