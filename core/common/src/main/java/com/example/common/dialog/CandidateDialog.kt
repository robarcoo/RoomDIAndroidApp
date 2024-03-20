package com.example.common.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.event.CandidateEvent
import com.example.common.viewmodel.CandidateState
import entity.Education
import entity.Experience

@Composable
fun CandidateDialog(state: CandidateState,
                    onEvent: (CandidateEvent) -> Unit,
                    modifier: Modifier = Modifier
    ) {
    AlertDialog(modifier = modifier, onDismissRequest = { CandidateEvent.HideDialog },
        dismissButton = {
        Button( onClick = {
        onEvent(CandidateEvent.HideDialog) }) {
            Text(text = "Cancel") }
                                                        },
        confirmButton = { Button( onClick = { onEvent(
        CandidateEvent.SaveCandidate) }) { Text (text = "Save Resume")} },
        title = { Text(text = "Edit candidate")},
    text = {
        Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.verticalScroll(rememberScrollState())) {
            TextField(
                value = "${state.name}",
                onValueChange = { onEvent(CandidateEvent.SetName(it))
                },
                placeholder = {
                    Text(text = "Full Name")
                }
            )
            TextField(
                value = "${state.profession}",
                onValueChange = { onEvent(CandidateEvent.SetProfession(it))
                },
                placeholder = {
                    Text(text = "Profession")
                }
            )
            TextField(
                value = "${state.sex}",
                onValueChange = { onEvent(CandidateEvent.SetSex(it))
                },
                placeholder = {
                    Text(text = "Sex")
                }
            )
            TextField(
                value = "${state.dateBirth}",
                onValueChange = { onEvent(CandidateEvent.SetDateOfBirth(it))
                },
                placeholder = {
                    Text(text = "Date of Birth")
                }
            )
            TextField(
                value = "${state.phone}",
                onValueChange = { onEvent(CandidateEvent.SetPhone(it))
                },
                placeholder = {
                    Text(text = "Phone")
                }
            )
            TextField(
                value = "${state.email}",
                onValueChange = { onEvent(CandidateEvent.SetEmail(it))
                },
                placeholder = {
                    Text(text = "Email")
                }
            )
            TextField(
                value = "${state.relocation}",
                onValueChange = { onEvent(CandidateEvent.SetRelocation(it))
                },
                placeholder = {
                    Text(text = "Relocation")
                }
            )
            var educationCount = -1
            state.education?.forEach {
                it.let { education ->
                    educationCount++
                    Spacer(modifier = Modifier.size(15.dp))
                    TextField(
                        value = "${education.type}",
                        onValueChange = { str -> onEvent(CandidateEvent.SetType(str, educationCount)) },
                        placeholder = {
                            Text(text = "Education Type")
                        }
                    )
                    TextField(
                        value = "${education.year_start}",
                        onValueChange = { str -> onEvent(CandidateEvent.SetEducationStartYear(str)) },
                        placeholder = {
                            Text(text = "Education Year Start")
                        }
                    )
                    TextField(
                        value = "${education.year_end}",
                        onValueChange = { str -> onEvent(CandidateEvent.SetEducationEndYear(str)) },
                        placeholder = {
                            Text(text = "Education Year End")
                        }
                    )
                    TextField(
                        value = "${education.description}",
                        onValueChange = { str -> onEvent(CandidateEvent.SetEducationDescription(str)) },
                        placeholder = {
                            Text(text = "Education Description")
                        }
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                }
            }
            Button(onClick = { state.education?.add(Education(state.id, "", "", "", ""))}) {
                Text("Add Education")
            }

            state.experience?.forEach {
                it?.let { experience ->
                    Spacer(modifier = Modifier.size(15.dp))
                    TextField(
                        value = "${ experience.company_name}",
                        onValueChange = { str -> onEvent(CandidateEvent.SetCompany(str)) },
                        placeholder = {
                            Text(text = "Company Name")
                        }
                    )
                    TextField(
                        value = "${experience.date_start}",
                        onValueChange = { str -> onEvent(CandidateEvent.SetJobStartYear(str)) },
                        placeholder = {
                            Text(text = "Started Working at Company")
                        }
                    )
                    TextField(
                        value = "${experience.date_end}",
                        onValueChange = { str -> onEvent(CandidateEvent.SetJobEndYear(str)) },
                        placeholder = {
                            Text(text = "Finished Working at Company")
                        }
                    )
                    TextField(
                        value = "${experience.description}",
                        onValueChange = { str -> onEvent(CandidateEvent.SetJobDescription(str)) },
                        placeholder = {
                            Text(text = "Former Job Description")
                        }
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                }
            }
            Button(onClick = { state.experience?.add(Experience(state.id, "", "", "", ""))}) {
                Text("Add Job Experience")
            }
            TextField(
                value = "${state.freeForm}",
                onValueChange = { onEvent(CandidateEvent.setFreeForm(it))
                },
                placeholder = {
                    Text(text = "Free Form")
                }
            )
        }
    },

    )

}