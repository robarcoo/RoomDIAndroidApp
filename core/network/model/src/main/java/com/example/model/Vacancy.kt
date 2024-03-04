package com.example.model

import kotlinx.serialization.Serializable


@Serializable
data class Vacancy (
    val id: Int = 0,
    val name: String = "",
    val level:String = "",
    val salary: Int = 0,
    val company: String = "",
)