package com.example.sr

import com.example.sr.extensions.assertNextStep
import com.example.sr.extensions.step
import com.example.sr.generators.UserGenerator
import io.qameta.allure.Step
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import reactor.kotlin.test.test
import kotlin.test.assertEquals

class UserServiceTests @Autowired constructor(
        private val userRepository: UserRepository,
        private val userService: UserService,
        private val util: UserUtil
) : TestBase() {

    @Test
    fun `find user by id`() {
        val user = userRepository.save(
                util.toEntity(
                        UserGenerator().generate()
                )
        ).map { util.fromEntity(it) }.block()!!

        userService
                .findById(user.id!!)
                .test()
                .`as`("check user")
                .assertNext {
                    assertEquals(user.id, it.id!!)
                    assertEquals(user.firstName, it.firstName)
                    assertEquals(user.lastName, it.lastName)
                }
                .verifyComplete()
    }

    @Test
    fun `find user by id with steps`() {
        val user = step<User>("generate user and save to DB") {
            userRepository.save(
                    util.toEntity(
                            UserGenerator().generate()
                    )
            ).map { util.fromEntity(it) }.block()!!
        }

        step("get user by id") {
            userService
                    .findById(user.id!!)
                    .test()
                    .assertNextStep("check user") {
                        assertEquals(user.id, it.id)
                        assertEquals(user.firstName, it.firstName)
                        assertEquals(user.lastName, it.lastName)
                    }
                    .verifyComplete()
        }
    }

    @Test
    fun `find user by id with steps as function`() {
        val user = generateUser()
        getUserById(user)
                .test()
                .assertNext {
                    checkUser(user, it)
                }
                .verifyComplete()
    }

    @Step("check user")
    internal fun checkUser(user: User, it: User) {
        assertEquals(user.id, it.id)
        assertEquals(user.firstName, it.firstName)
        assertEquals(user.lastName, it.lastName)
    }

    @Step("get user by id")
    internal fun getUserById(user: User) =
            userService
                    .findById(user.id!!)

    @Step("generate user and save to DB")
    internal fun generateUser() = userRepository.save(
            util.toEntity(
                    UserGenerator().generate()
            )
    ).map { util.fromEntity(it) }.block()!!

}
