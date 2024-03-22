package com.example.common.viewmodel

import androidx.compose.runtime.mutableStateListOf
import entity.Candidate
import entity.Education
import entity.Experience

data class CandidateState(
    val id: Int = 0,
    val isAddingCandidate: Boolean = false,
    val isEditingCandidate: Boolean = false,
    var candidates: MutableList<Candidate> = mutableStateListOf(),
    var name: String? = "",
    var profession: String? = "",
    var sex: String? = "",
    var dateBirth: String? = "",
    var email: String? = "",
    var phone: String? = "",
    var relocation: String? = "",
    var education: MutableList<Education> = mutableStateListOf(),
    val experience: MutableList<Experience> = mutableStateListOf(),
    var freeForm: String? = ""
    )