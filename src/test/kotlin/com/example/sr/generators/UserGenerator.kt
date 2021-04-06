package com.example.sr.generators

import com.example.sr.UserEntity
import io.github.serpro69.kfaker.Faker
import org.springframework.boot.test.context.TestComponent
import java.util.*

@TestComponent
class UserGenerator: TestObjectGenerator<UserEntity> {

    private val id = null
    private var firstName = { Faker().name.firstName() }
    private var lastName = { Faker().name.lastName() }

    fun withFirstName(value: String) = this.also { it.firstName = { value } }
    fun withLastName(value: String) = this.also { it.lastName = { value } }

    override fun generate(): UserEntity {
        return UserEntity(
            id,
            firstName(),
            lastName()
        )
    }
}

data class User(
    val firstName: String,
    val lastName: String
)