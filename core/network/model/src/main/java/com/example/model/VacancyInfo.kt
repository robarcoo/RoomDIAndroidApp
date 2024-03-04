package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class VacancyInfo(
    val id: Int = 0,
    val profession: String = "",
    val level: String = "",
    val salary: Int = 0,
    val description: String = "The best job ever, earn 300K/nanosec"
)
