package com.example.common.viewmodel

import entity.Candidate
import entity.CandidateInfo
import entity.Education
import entity.Experience

data class CandidateState(
    val isAddingCandidate: Boolean = false,
    var candidates: List<Candidate> = emptyList(),
    val candidateInfo: CandidateInfo? = null,
    val education: List<Education?>? = emptyList(),
    val experience: List<Experience?>? = emptyList(),
    var freeForm: String? = ""
    )