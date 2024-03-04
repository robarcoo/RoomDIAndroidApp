package com.example.model
import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val id: Int = 0,
    val name: String = "",
    val activity: String = "",
    val vacancies: List<VacancyInfo> = listOf(),
    val contacts: String = "8 800 555 35 35"
)