package com.example.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.event.CandidateEvent
import com.example.database.dao.CandidateDao
import com.example.database.entity.Candidate
import com.example.database.local.LocalDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CandidateViewModel @Inject constructor(
    private val repository: CandidateDao
) : ViewModel() {

    val _state = MutableStateFlow(CandidateState())

    fun onEvent(event : CandidateEvent) {
        when(event) {
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
                val candidateInfo = _state.value.candidateInfo
                val education = _state.value.education
                val experience = _state.value.experience
                val freeForm = _state.value.freeForm

                if (candidateInfo == null || education.isEmpty() || experience.isEmpty() || freeForm.isBlank()) {
                    return
                }

                val candidate = candidateInfo.let {
                    Candidate(
                        candidateInfo = it,
                        education = education,
                        experience = experience,
                        freeForm = freeForm
                    )
                }

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
            is CandidateEvent.deleteCandidate -> {
                viewModelScope.launch {
                    repository.deleteCandidate(event.candidate)
                }
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