package com.example.sr

import com.example.sr.generators.UserGenerator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import reactor.kotlin.test.test
import kotlin.test.assertEquals

class UserRepositoryTests @Autowired constructor(
        private val userRepositoryHandMade: UserRepositoryHandMade,
        private val userRepository: UserRepository,
): TestBase() {

    @Test
    fun `find user by id with annotate steps`() {
        val userEntity = UserGenerator().generate()
        val user = userRepository.save(userEntity).block()
        userRepositoryHandMade
                .findById(user!!.id)
                .test()
                .`as`("check user")
                .expectNextCount(1)
                .assertNext {
                    assertEquals(user.firstName, it.firstName)
                    assertEquals(user.lastName, it.lastName)
                    assertEquals(user.id, it.id)
                }
                .verifyComplete()
    }

    @Test
    fun `find user by id with extensions steps`() {
        val user = userRepository.save(UserGenerator().generate()).block()
        userRepositoryHandMade
                .findById(user?.id)
                .test()
                .`as`("check user")
                .expectNextCount(1)
                .assertNext {
                    assertEquals(user!!.firstName, it.firstName)
                    assertEquals(user.lastName, it.lastName)
                    assertEquals(user.id, it.id)
                }
                .verifyComplete()
    }
}