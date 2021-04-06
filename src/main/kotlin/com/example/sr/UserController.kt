package com.example.sr

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class UserController(
        private val userRepositoryHandMade: UserRepositoryHandMade, // типа сравнить как я написал и как это реализовано в спринге
        private val userRepository: UserRepository
) {

    @GetMapping("/users")
    fun getUser(): Mono<ResponseEntity<List<UserEntity>>> {
        return userRepository
                .findAll()
                .collectList()
                .map { ResponseEntity.ok(it) }
                .metrics()
    }

    @GetMapping("/hand-made/users")
    fun getUsersHandMade(): Mono<ResponseEntity<List<UserEntity>>> {
        return userRepositoryHandMade
                .findAll()
                .collectList()
                .map { ResponseEntity.ok(it) }
                .metrics()
    }

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: String): Mono<ResponseEntity<UserView>> {
        return userRepository
                .findById(id.toLong())
                .map { UserView(it.id!!, it.firstName, it.lastName) }
                .map { ResponseEntity.ok(it) }
                .metrics()
    }

    @GetMapping("/hand-made/users/{id}")
    fun getUserByIdHandMade(@PathVariable id: String): Mono<ResponseEntity<UserView>> {
        return userRepositoryHandMade
                .findById(id.toLong())
                .map { UserView(it.id!!, it.firstName, it.lastName) }
                .map { ResponseEntity.ok(it) }
                .metrics()
    }

}

data class UserView(
    val id: Long,
    val firstName: String,
    val lastName: String
)
