package com.example.sr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.scheduler.Schedulers

@SpringBootApplication
class SrApplication

fun main(args: Array<String>) {
    Schedulers.enableMetrics()
    runApplication<SrApplication>(*args)
}
