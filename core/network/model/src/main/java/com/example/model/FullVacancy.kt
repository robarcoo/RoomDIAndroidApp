package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class FullVacancy (
    val id: Int = 0,
    val name: String = "",
    val level:String = "",
    val salary: Int = 0,
    val companyid : Int = 0,
    val company: String = "",
    val field: String = "",
    val description: String = "",
    val phone: String = ""
)