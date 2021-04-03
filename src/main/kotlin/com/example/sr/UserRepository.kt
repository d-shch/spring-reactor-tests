package com.example.sr

import reactor.core.publisher.Mono

class UserRepository {

    fun create(user: User): Mono<User>

}