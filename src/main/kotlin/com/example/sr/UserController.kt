package com.example.sr

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class UserController{

    @GetMapping("/users")
    fun getUsers(): Mono<ResponseEntity<String>> {
        return Mono.just(ResponseEntity.ok("PASSSS")).metrics()
    }

    @GetMapping("/users/{userId}")
    fun getUserById(
            @PathVariable userId: String
    ): Mono<ResponseEntity<String>> {
        return Mono.just(ResponseEntity.ok("${userId} + THIS IS USER ID")).metrics()
    }

    @PostMapping("/user")
    fun test(): Mono<ResponseEntity<String>> {
        return Mono.just(ResponseEntity.ok("PASSSS")).metrics()
    }

}