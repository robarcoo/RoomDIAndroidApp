package com.example.common.event

import entity.Candidate

sealed interface CandidateEvent {
    data object SaveCandidate: CandidateEvent
    data class SetName(val name: String?): CandidateEvent
    data class SetProfession(val profession: String?): CandidateEvent
    data class SetSex(val sex: String?) : CandidateEvent
    data class SetDateOfBirth(val date: String?) : CandidateEvent
    data class SetEmail(val email: String?) : CandidateEvent

    data class SetPhone(val phone: String?) : CandidateEvent
    data class SetRelocation(val relocation: String?): CandidateEvent

    data class addEducation(val index: Int) :
        CandidateEvent

    data class addExperience(val index: Int) :
        CandidateEvent
    data class SetType(val type: String?, val index: Int) : CandidateEvent
    data class SetEducationStartYear(val startYear: String?, val index: Int) : CandidateEvent
    data class SetEducationEndYear(val endYear: String?, val index: Int) : CandidateEvent
    data class SetEducationDescription(val description: String?, val index: Int): CandidateEvent

    data class SetCompany(val company: String?, val index: Int) : CandidateEvent
    data class SetJobStartYear(val startYear: String?, val index: Int) : CandidateEvent
    data class SetJobEndYear(val endYear: String?, val index: Int) : CandidateEvent
    data class SetJobDescription(val description: String?, val index: Int) : CandidateEvent
    data class setFreeForm(val freeForm: String?) : CandidateEvent
    data object HideDialog: CandidateEvent
    data object NewCandidate: CandidateEvent

    data class EditCandidate(val candidate: Candidate): CandidateEvent
    data class deleteCandidate(val candidate: Candidate) :
        CandidateEvent
}