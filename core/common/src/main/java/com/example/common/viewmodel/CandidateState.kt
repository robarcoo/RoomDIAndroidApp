package com.example.common.viewmodel

import com.example.database.entity.Candidate
import com.example.database.entity.CandidateInfo
import com.example.database.entity.Contact
import com.example.database.entity.Education
import com.example.database.entity.Experience

data class CandidateState(
    val isAddingCandidate: Boolean = false,
    val candidates: List<Candidate> = emptyList(),
    val candidateInfo: CandidateInfo? = null,
    val education: List<Education> = emptyList(),
    val experience: List<Experience> = emptyList(),
    val freeForm: String = ""
    )