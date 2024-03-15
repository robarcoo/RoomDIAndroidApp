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
                value = "${state.name}",
                onValueChange = { onEvent(CandidateEvent.SetName(state.name))
                },
                placeholder = {
                    Text(text = "Full Name")
                }
            )
            TextField(
                value = "${state.profession}",
                onValueChange = { onEvent(CandidateEvent.SetName(state.profession))
                },
                placeholder = {
                    Text(text = "Profession")
                }
            )
            TextField(
                value = "${state.sex}",
                onValueChange = { onEvent(CandidateEvent.SetName(state.sex))
                },
                placeholder = {
                    Text(text = "Sex")
                }
            )
            TextField(
                value = "${state.dateBirth}",
                onValueChange = { onEvent(CandidateEvent.SetName(state.dateBirth))
                },
                placeholder = {
                    Text(text = "Date of Birth")
                }
            )
            TextField(
                value = "${state.phone}",
                onValueChange = { onEvent(CandidateEvent.SetName(state.phone))
                },
                placeholder = {
                    Text(text = "Phone")
                }
            )
            TextField(
                value = "${state.email}",
                onValueChange = { onEvent(CandidateEvent.SetName(state.email))
                },
                placeholder = {
                    Text(text = "Email")
                }
            )
            TextField(
                value = "${state.relocation}",
                onValueChange = { onEvent(CandidateEvent.SetName(state.relocation))
                },
                placeholder = {
                    Text(text = "Relocation")
                }
            )
            TextField(
                value = "${state.education}",
                onValueChange = { onEvent(CandidateEvent.saveWithoutChangesEducation(state.education))
                },
                placeholder = {
                    Text(text = "Candidate Info Input")
                }
            )
            TextField(
                value = "${state.experience}",
                onValueChange = { onEvent(CandidateEvent.saveWithoutChangesExperience(state.experience))
                },
                placeholder = {
                    Text(text = "Candidate Info Input")
                }
            )
            TextField(
                value = "${state.freeForm}",
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