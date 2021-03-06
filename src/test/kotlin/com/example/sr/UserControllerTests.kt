package com.example.sr

import com.example.sr.generators.UserGenerator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.test.web.reactive.server.WebTestClient

@AutoConfigureWebTestClient
class UserControllerTests @Autowired constructor(
        private val webTestClient: WebTestClient,
        private val userRepository: UserRepository,
        private val util: UserUtil
) : TestBase() {

    @Test
    fun `find user by id`() {
        val userEntity = UserGenerator().generate()
        val user = userRepository.save(util.toEntity(userEntity)).block()

        webTestClient
                .get()
                .uri("/users/${user!!.id}")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$.id").isNotEmpty
                .jsonPath("$.firstName").isEqualTo(user.firstName)
                .jsonPath("$.lastName").isEqualTo(user.lastName)
    }

}