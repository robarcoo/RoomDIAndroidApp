package com.example.common.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.common.event.CandidateEvent
import com.example.common.viewmodel.CandidateState

@Composable
fun CandidateDialog(state: CandidateState,
                    onEvent: (CandidateEvent) -> Unit,
                    modifier: Modifier = Modifier
    ) {
    AlertDialog(modifier = modifier, onDismissRequest = { onEvent(CandidateEvent.HideDialog) }, confirmButton = { onEvent(
        CandidateEvent.SaveCandidate) },
        title = { Text(text = "Edit candidate")},
    text = {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            TextField(
                value = state.candidateInfo.toString(),
                onValueChange = { onEvent(CandidateEvent.setCandidateInfo(state.candidateInfo!!))
                },
                placeholder = {
                    Text(text = "Candidate Info Input")
                }
            )
            TextField(
                value = state.education.toString(),
                onValueChange = { onEvent(CandidateEvent.setEducation(state.education))
                },
                placeholder = {
                    Text(text = "Candidate Info Input")
                }
            )
            TextField(
                value = state.experience.toString(),
                onValueChange = { onEvent(CandidateEvent.setExperience(state.experience))
                },
                placeholder = {
                    Text(text = "Candidate Info Input")
                }
            )
            TextField(
                value = state.freeForm.toString(),
                onValueChange = { onEvent(CandidateEvent.setFreeForm(it))
                },
                placeholder = {
                    Text(text = "Candidate Info Input")
                }
            )
        }
    }
    )

}