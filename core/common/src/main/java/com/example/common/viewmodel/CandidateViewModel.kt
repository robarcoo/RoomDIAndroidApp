package com.example.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.event.CandidateEvent
import com.example.repository.CandidateRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import entity.Candidate
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
    var candidates = MutableStateFlow(CandidateState())
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadCandidates().collect {
                _state.value.candidates = it
            }
        }
    }

    val state = combine(_state, candidates) {
        state, candidates ->
        state.copy(
            candidates = candidates.candidates
        )
    }.flowOn(Dispatchers.IO).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CandidateState())

    fun onEvent(event : CandidateEvent) {
        when(event) {
            is CandidateEvent.deleteCandidate -> {
                viewModelScope.launch(Dispatchers.IO) {
                    repository.deleteCandidate(event.candidate)
                    _state.update {
                        it.copy(
                            isAddingCandidate = false,
                            emptyList(),
                            candidateInfo = null,
                            emptyList(),
                            emptyList(),
                            freeForm = ""
                        )
                    }
                }
            }
            CandidateEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingCandidate = false
                )}
            }
            CandidateEvent.OpenDialog -> {
                _state.update { it.copy(
                    isAddingCandidate = true
                )}
            }
            CandidateEvent.SaveCandidate -> {
                val candidateInfo = state.value.candidateInfo
                val education = state.value.education
                val experience = state.value.experience
                val freeForm = state.value.freeForm

                if (candidateInfo == null || education?.isEmpty() == true || experience?.isEmpty() == true || freeForm?.isBlank() == true) {
                    return
                }

                val candidate = Candidate(
                    candidate_info = candidateInfo,
                    education = education,
                    job_experience = experience,
                    free_form = freeForm
                )

                viewModelScope.launch {
                    repository.insertCandidate(candidate)
                }

                _state.update { it.copy(
                    isAddingCandidate = false,
                    emptyList(),
                    candidateInfo = null,
                    emptyList(),
                    emptyList(),
                    freeForm = ""
                )}
            }

            is CandidateEvent.setCandidateInfo -> {
                _state.update { it.copy (
                    candidateInfo = event.candidateInfo
                )
                }
            }
            is CandidateEvent.setEducation -> {
                _state.update { it.copy (
                    education = event.education
                ) }
            }
            is CandidateEvent.setExperience -> {
                _state.update { it.copy (
                    experience = event.experience
                )}
            }
            is CandidateEvent.setFreeForm -> {
                _state.update { it.copy (
                    freeForm = event.freeForm
                )}
            }
        }
    }
}