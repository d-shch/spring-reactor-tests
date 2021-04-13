package com.example.sr

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.test.test
import kotlin.test.assertEquals

class ReactorExamples {

    @Test
    fun `flux example`() {
        val flux: Flux<Int> = Flux.just(1, 2, 3)

        flux.subscribe { println(it) } // 1, 2, 3

        flux.take(2).subscribe { println(it) } // 1, 2

        flux.map { it + 5 }
                .doOnNext { println("Next element after logic: $it") }
                .doOnSubscribe { println("We have subscribed to flux") }
                .doOnComplete { println("Flux has completed") }
                .subscribe()
    }

    @Test
    fun `mono example`() {
        val mono: Mono<Int> = Mono.just(1)
        mono.map { println(it) }.subscribe()
    }

    @Test
    fun `always green`() {
        val mono: Mono<Int> = Mono.just(1)
        mono.subscribe { assertEquals(5, it) }
    }

    @Test
    fun `always red`() {
        val mono: Mono<Int> = Mono.just(1)
        mono
                .test()
                .assertNext { assertEquals(5, it) }
                .verifyComplete()
    }


}