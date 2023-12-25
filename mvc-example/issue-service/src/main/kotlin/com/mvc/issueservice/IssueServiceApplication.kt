package com.mvc.issueservice

import mu.KLogger
import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class IssueServiceApplication
fun <R : Any> R.log(): KLogger {
	return KotlinLogging.logger(this::class.qualifiedName!!)
}

fun main(args: Array<String>) {
	runApplication<IssueServiceApplication>(*args)
}
