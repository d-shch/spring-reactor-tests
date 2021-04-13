package com.example.sr

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class UserEntity(
        @Id
        var id: Long?,
        var firstName: String,
        var lastName: String
)