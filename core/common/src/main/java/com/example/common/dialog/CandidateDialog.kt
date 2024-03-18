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
                onValueChange = { onEvent(CandidateEvent.SetName(state.name))
                },
                placeholder = {
                    Text(text = "Full Name")
                }
            )
            TextField(
                value = "${state.profession}",
                onValueChange = { onEvent(CandidateEvent.SetProfession(state.profession))
                },
                placeholder = {
                    Text(text = "Profession")
                }
            )
            TextField(
                value = "${state.sex}",
                onValueChange = { onEvent(CandidateEvent.SetSex(state.sex))
                },
                placeholder = {
                    Text(text = "Sex")
                }
            )
            TextField(
                value = "${state.dateBirth}",
                onValueChange = { onEvent(CandidateEvent.SetDateOfBirth(state.dateBirth))
                },
                placeholder = {
                    Text(text = "Date of Birth")
                }
            )
            TextField(
                value = "${state.phone}",
                onValueChange = { onEvent(CandidateEvent.SetPhone(state.phone))
                },
                placeholder = {
                    Text(text = "Phone")
                }
            )
            TextField(
                value = "${state.email}",
                onValueChange = { onEvent(CandidateEvent.SetEmail(state.email))
                },
                placeholder = {
                    Text(text = "Email")
                }
            )
            TextField(
                value = "${state.relocation}",
                onValueChange = { onEvent(CandidateEvent.SetRelocation(state.relocation))
                },
                placeholder = {
                    Text(text = "Relocation")
                }
            )
            state.education?.forEach {
                it?.let { education ->
                    Spacer(modifier = Modifier.size(15.dp))
                    TextField(
                        value = "${ education.type}",
                        onValueChange = { onEvent(CandidateEvent.SetType(education.type)) },
                        placeholder = {
                            Text(text = "Education Type")
                        }
                    )
                    TextField(
                        value = "${education.year_start}",
                        onValueChange = { onEvent(CandidateEvent.SetEducationStartYear(education.year_start)) },
                        placeholder = {
                            Text(text = "Education Year Start")
                        }
                    )
                    TextField(
                        value = "${education.year_end}",
                        onValueChange = { onEvent(CandidateEvent.SetEducationEndYear(education.year_end)) },
                        placeholder = {
                            Text(text = "Education Year End")
                        }
                    )
                    TextField(
                        value = "${education.description}",
                        onValueChange = { onEvent(CandidateEvent.SetEducationDescription(education.description)) },
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
                        onValueChange = { onEvent(CandidateEvent.SetCompany(experience.company_name)) },
                        placeholder = {
                            Text(text = "Company Name")
                        }
                    )
                    TextField(
                        value = "${experience.date_start}",
                        onValueChange = { onEvent(CandidateEvent.SetJobStartYear(experience.date_start)) },
                        placeholder = {
                            Text(text = "Started Working at Company")
                        }
                    )
                    TextField(
                        value = "${experience.date_end}",
                        onValueChange = { onEvent(CandidateEvent.SetJobEndYear(experience.date_end)) },
                        placeholder = {
                            Text(text = "Finished Working at Company")
                        }
                    )
                    TextField(
                        value = "${experience.description}",
                        onValueChange = { onEvent(CandidateEvent.SetJobDescription(experience.description)) },
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