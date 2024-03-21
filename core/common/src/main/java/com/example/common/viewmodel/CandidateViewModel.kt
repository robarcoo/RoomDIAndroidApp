package com.example.common.viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.event.CandidateEvent
import com.example.repository.CandidateRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import entity.Candidate
import entity.CandidateInfo
import entity.Contact
import entity.Education
import entity.Experience
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CandidateViewModel @Inject constructor(
    private val repository: CandidateRepositoryImpl
) : ViewModel() {

    var _state = MutableStateFlow(CandidateState())
    private var candidates = MutableStateFlow(CandidateState())
    init {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                repository.loadCandidates().collect {
                    _state.value.candidates = it
                }
            } catch (e : Exception) {
                repository.loadCandidatesOffline().collect {
                    _state.value.candidates = it
                }

        }
        }
    }

    private val state = combine(_state, candidates) {
        state, candidates ->
        state.copy(
            candidates = candidates.candidates
        )
    }.flowOn(Dispatchers.IO).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CandidateState())

    private fun getLastId() : Int {
        var id = 0
        viewModelScope.launch {
            id = repository.getLastId().toInt()
        }
        return id
    }
    private fun cleanState(candidateState: CandidateState) : CandidateState{
        return candidateState.copy(
            0,
            isAddingCandidate = false,
            isEditingCandidate = false,
            isAddingEducation = false,
            isAddingExperience = false,
            name = "",
            profession = "",
            sex = "",
            dateBirth = "",
            email = "",
            phone = "",
            relocation = "",
            education = SnapshotStateList(),
            type = "",
            educationYearStart = "",
            educationYearEnd = "",
            educationDescription = "",
            experience = SnapshotStateList(),
            company = "",
            jobYearStart = "",
            jobYearEnd = "",
            jobDescription = "",
            freeForm = ""
        )
    }
    fun onEvent(event : CandidateEvent) {
        when(event) {
            is CandidateEvent.deleteCandidate -> {
                    viewModelScope.launch(Dispatchers.IO) {
                        val success = repository.deleteCandidate(event.candidate)
                        if (success) {
                            _state.update {
                                cleanState(it)
                            }

                        }
                    }
            }
            CandidateEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingCandidate = false,
                    isEditingCandidate = false
                )}
            }
            CandidateEvent.NewCandidate -> {
                _state.update { it.copy(
                    id = getLastId() + 1,
                    isAddingCandidate = true
                )}
            }
            is CandidateEvent.EditCandidate -> {
                _state.update { it.copy(
                    event.candidate.id,
                    isAddingCandidate = false,
                    isEditingCandidate = true,
                    isAddingEducation = false,
                    isAddingExperience = false,
                    name = event.candidate.candidate_info?.name,
                    profession = event.candidate.candidate_info?.profession,
                    sex = event.candidate.candidate_info?.sex,
                    dateBirth = event.candidate.candidate_info?.birth_date,
                    email = event.candidate.candidate_info?.contacts?.email,
                    phone = event.candidate.candidate_info?.contacts?.phone,
                    relocation = event.candidate.candidate_info?.relocation,
                    education = event.candidate.education?.toMutableList() ?: mutableListOf(),
                    experience = event.candidate.job_experience?.toMutableList() ?: mutableListOf(),
                    freeForm = event.candidate.free_form
                )}

            }

            is CandidateEvent.saveWithChangesEducation -> {
                val candidate_id = state.value.id
                val type = state.value.type
                val educationStartYear = state.value.educationYearStart
                val educationEndYear = state.value.educationYearEnd
                val educationDescription = state.value.educationDescription

                if (type == "" ||
                    educationStartYear == "" ||
                    educationEndYear == "" ||
                    educationDescription == "") {
                    return
                }

                val education = Education(candidate_id,
                    type,
                    educationStartYear,
                    educationEndYear,
                    educationDescription)
                val newEducation = _state.value.education
                newEducation.add(education)
                _state.update { candidate ->
                    candidate.copy(
                        education = newEducation
                    )
                }
            }

            is CandidateEvent.saveWithChangesExperience -> {
                val candidate_id = state.value.id
                val company = state.value.company
                val jobStartYear = state.value.jobYearStart
                val jobEndYear = state.value.jobYearEnd
                val jobDescription = state.value.jobDescription


                if (company == "" || jobStartYear == "" ||
                    jobEndYear == "" || jobDescription == "") {
                    return
                }

                val jobExperience = Experience(
                    candidate_id,
                    company,
                    jobStartYear,
                    jobEndYear,
                    jobDescription
                )

                val newJobExperience = _state.value.experience
                newJobExperience.add(jobExperience)
                _state.update {
                    job ->
                    job.copy(
                        experience = newJobExperience
                    )
                }

            }

            CandidateEvent.SaveCandidate -> {

                val candidate_id = _state.value.id
                val name = _state.value.name
                val sex = _state.value.sex
                val dateBirth = _state.value.dateBirth
                val profession = _state.value.profession
                val email = _state.value.email
                val phone = _state.value.phone
                val relocation = _state.value.relocation
                val education = _state.value.education
                val experience = _state.value.experience
                val freeForm = _state.value.freeForm


                if (name == "" ||
                    sex == "" ||
                    profession == "" ||
                    email == "" ||
                    phone == "" ||
                    relocation == "" || freeForm?.isBlank() == true
                ) {
                    return
                }
                val candidate = Candidate(
                    candidate_info = CandidateInfo(
                        candidate_id = candidate_id,
                        name = name,
                        sex = sex,
                        profession = profession,
                        birth_date = dateBirth,
                        contacts = Contact(candidate_id, phone, email),
                        relocation = relocation),
                    education = education,
                    job_experience = experience,
                    free_form = freeForm
                )
                viewModelScope.launch(Dispatchers.IO) {
                    val success =  if (_state.value.isEditingCandidate) {
                        repository.editCandidate(candidate, candidate_id)
                    } else {
                        repository.insertCandidate(candidate)
                    }
                    if (success) {
                        _state.update {
                            cleanState(it)
                        }
                    }

                }

            }


            is CandidateEvent.saveWithoutChangesEducation -> {
                _state.update { it.copy (
                    education = event.education
                ) }
            }
            is CandidateEvent.saveWithoutChangesExperience -> {
                _state.update { it.copy (
                    experience = event.experience
                )}
            }
            is CandidateEvent.setFreeForm -> {
                _state.update { it.copy (
                    freeForm = event.freeForm
                )}
            }

            is CandidateEvent.SetName -> {
                _state.update { it.copy (
                    name = event.name
                )
                }
            }

            is CandidateEvent.SetDateOfBirth -> {
                _state.update {
                    it.copy(
                        dateBirth = event.date
                    )
                }
            }

            is CandidateEvent.SetProfession -> {
                _state.update {
                    it.copy(
                        profession = event.profession
                    )
                }
            }

            is CandidateEvent.SetSex -> {
                _state.update {
                    it.copy(
                        sex = event.sex
                    )
                }
            }

            is CandidateEvent.SetRelocation -> {
                _state.update {
                    it.copy(
                        relocation = event.relocation
                    )
                }
            }

            is CandidateEvent.SetEmail -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }

            is CandidateEvent.SetPhone -> {
                _state.update {
                    it.copy(
                        phone = event.phone
                    )
                }
            }

            is CandidateEvent.SetType -> {
                val old = _state.value.education[event.index]
                _state.value.education[event.index] = Education(old.candidate_id, event.type, old.year_start, old.year_end, old.description, old.id)
            }

            is CandidateEvent.SetEducationStartYear -> {
                val old = _state.value.education[event.index]
                _state.value.education[event.index] = Education(old.candidate_id, old.type, event.startYear, old.year_end, old.description, old.id)
            }

            is CandidateEvent.SetEducationEndYear -> {
                val old = _state.value.education[event.index]
                _state.value.education[event.index] = Education(old.candidate_id, old.type, old.year_start, event.endYear, old.description, old.id)
            }

            is CandidateEvent.SetEducationDescription -> {
                val old = _state.value.education[event.index]
                _state.value.education[event.index] = Education(old.candidate_id, old.type, old.year_start, old.year_end, event.description, old.id)
            }

            is CandidateEvent.SetCompany -> {
                val old = _state.value.experience[event.index]
                _state.value.experience[event.index] = Experience(old.candidate_id, old.date_start, old.date_end, event.company, old.description, old.id)
            }

            is CandidateEvent.SetJobStartYear -> {
                val old = _state.value.experience[event.index]
                _state.value.experience[event.index] = Experience(old.candidate_id, event.startYear, old.date_end, old.company_name, old.description, old.id)
            }

            is CandidateEvent.SetJobEndYear -> {
                val old = _state.value.experience[event.index]
                _state.value.experience[event.index] = Experience(old.candidate_id,  old.date_start, event.endYear, old.company_name, old.description, old.id)
            }

            is CandidateEvent.SetJobDescription -> {
                val old = _state.value.experience[event.index]
                _state.value.experience[event.index] = Experience(old.candidate_id,  old.date_start, old.date_end, old.company_name, event.description, old.id)
            }
        }
    }
}