package com.example.sr

data class User(
        var id: UserId?,
        var firstName: FirstName,
        var lastName: LastName
)

data class UserId(val value: Long)

data class FirstName(val value: String)

data class LastName(val value: String)