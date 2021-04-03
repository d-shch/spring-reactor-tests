package com.example.sr

import org.springframework.data.annotation.Id
import java.util.*

data class CarEntity(
    @Id
    val id: UUID,
    val brand: String,
    val model: String,
)