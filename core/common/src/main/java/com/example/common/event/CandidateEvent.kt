package com.example.common.event

import entity.Candidate
import entity.CandidateInfo
import entity.Education
import entity.Experience

sealed interface CandidateEvent {
    data object SaveCandidate: CandidateEvent
    data class setCandidateInfo(val candidateInfo: CandidateInfo?) :
        CandidateEvent
    data class setEducation(val education: List<Education?>?) :
        CandidateEvent
    data class setExperience(val experience: List<Experience?>?) :
        CandidateEvent
    data class setFreeForm(val freeForm: String?) : CandidateEvent
    data object HideDialog: CandidateEvent
    data object OpenDialog: CandidateEvent
    data class deleteCandidate(val candidate: Candidate) :
        CandidateEvent
}