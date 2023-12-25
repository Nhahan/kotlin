package com.mvc.issueservice

import mu.KLogger
import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IssueServiceApplication
fun <R : Any> R.log(): KLogger {
	return KotlinLogging.logger(this::class.qualifiedName!!)
}
fun main(args: Array<String>) {
	runApplication<IssueServiceApplication>(*args)
}
