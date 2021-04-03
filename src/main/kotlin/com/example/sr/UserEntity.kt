package com.example.sr

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("users")
data class UserEntity(

    @Id
    val id: UUID,
    val firstName: String,
    val lastName: String

)