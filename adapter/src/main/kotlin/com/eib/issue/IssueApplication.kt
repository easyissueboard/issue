package com.eib.issue

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class IssueApplication

fun main(args: Array<String>) {
    runApplication<IssueApplication>(*args)
}