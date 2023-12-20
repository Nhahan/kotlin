package com.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveAuthApplication

fun main(args: Array<String>) {
    runApplication<ReactiveAuthApplication>(*args)
}
