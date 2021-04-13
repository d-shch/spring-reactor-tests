package com.example.sr

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class UserController(
        private val userService: UserService,
        private val userRepository: UserRepository,
        private val util: UserUtil
) {

    @GetMapping("/users")
    fun getUser(): Flux<ResponseEntity<UserView>> {
        return userRepository
                .findAll()
                .map { util.toView(it) }
                .map { ResponseEntity.ok(it) }
                .metrics()
    }

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: String): Mono<ResponseEntity<UserView>> {
        return userService
                .findById(UserId(id.toLong()))
                .map { util.toView(it) }
                .map { ResponseEntity.ok(it) }
                .metrics()
    }

}
