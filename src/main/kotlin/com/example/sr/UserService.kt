package com.example.sr

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService(
        private val userRepository: UserRepository,
        private val util: UserUtil
) {

    fun findById(id: UserId): Mono<User> {
        return userRepository
                .findById(id.value)
                .map { util.fromEntity(it) }
    }

    fun findAll(): Flux<User> {
        return userRepository
                .findAll()
                .map { util.fromEntity(it) }
    }

}
