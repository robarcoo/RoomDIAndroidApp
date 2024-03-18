package com.example.common.viewmodel

import entity.Candidate
import entity.Education
import entity.Experience

data class CandidateState(
    val id: Int = 0,
    val isAddingCandidate: Boolean = false,
    val isEditingCandidate: Boolean = false,
    val isAddingEducation: Boolean = false,
    val isAddingExperience: Boolean = false,
    var candidates: List<Candidate> = emptyList(),
    var name : String? = "",
    var profession: String? = "",
    var sex: String? = "",
    var dateBirth: String? = "",
    var email : String? = "",
    var phone: String? = "",
    var relocation: String? = "",
    val education: MutableList<Education?>? = mutableListOf(),
    var type : String? = "",
    var educationYearStart : String? = "",
    var educationYearEnd: String? = "",
    var educationDescription: String? = "",
    val experience: MutableList<Experience?>? = mutableListOf(),
    var company: String? = "",
    var jobYearStart: String? = "",
    var jobYearEnd: String? = "",
    var jobDescription: String? = "",
    var freeForm: String? = ""
    )