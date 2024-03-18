package com.example.common.event

import entity.Candidate
import entity.Education
import entity.Experience

sealed interface CandidateEvent {
    data object SaveCandidate: CandidateEvent
    data class SetName(val name: String?): CandidateEvent
    data class SetProfession(val profession: String?): CandidateEvent
    data class SetSex(val sex: String?) : CandidateEvent
    data class SetDateOfBirth(val date: String?) : CandidateEvent
    data class SetEmail(val email: String?) : CandidateEvent

    data class SetPhone(val phone: String?) : CandidateEvent
    data class SetRelocation(val relocation: String?): CandidateEvent

    data class saveWithChangesEducation(val education: MutableList<Education?>?) :
        CandidateEvent
    data class saveWithoutChangesEducation(val education: MutableList<Education?>?) :
        CandidateEvent
    data class SetType(val type: String?) : CandidateEvent
    data class SetEducationStartYear(val startYear: String?) : CandidateEvent
    data class SetEducationEndYear(val endYear: String?) : CandidateEvent
    data class SetEducationDescription(val description: String?): CandidateEvent

    data class SetCompany(val company: String?) : CandidateEvent
    data class SetJobStartYear(val startYear: String?) : CandidateEvent
    data class SetJobEndYear(val endYear: String?) : CandidateEvent
    data class SetJobDescription(val description: String?) : CandidateEvent
    data class saveWithChangesExperience(val experience: MutableList<Experience?>?) :
        CandidateEvent
    data class saveWithoutChangesExperience(val experience: MutableList<Experience?>?) :
        CandidateEvent
    data class setFreeForm(val freeForm: String?) : CandidateEvent
    data object HideDialog: CandidateEvent
    data object NewCandidate: CandidateEvent

    data class EditCandidate(val candidate: Candidate): CandidateEvent
    data class deleteCandidate(val candidate: Candidate) :
        CandidateEvent
}