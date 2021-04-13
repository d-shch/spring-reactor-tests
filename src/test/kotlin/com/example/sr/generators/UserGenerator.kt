package com.example.sr.generators

import com.example.sr.FirstName
import com.example.sr.LastName
import com.example.sr.User
import com.example.sr.UserId
import io.github.serpro69.kfaker.Faker

class UserGenerator : TestObjectGenerator<User> {

    private val id: UserId? = null
    private var firstName = { FirstName(Faker().name.firstName()) }
    private var lastName = { LastName(Faker().name.lastName()) }

    fun withFirstName(value: FirstName) = this.also { it.firstName = { value } }
    fun withLastName(value: LastName) = this.also { it.lastName = { value } }

    override fun generate(): User {
        return User(
                id,
                firstName(),
                lastName()
        )
    }
}

