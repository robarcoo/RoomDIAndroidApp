
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.common.dialog.CandidateDialog
import com.example.common.event.CandidateEvent
import com.example.common.viewmodel.CandidateState

@Composable
fun CandidateScreen(state: CandidateState, onEvent: (CandidateEvent) -> Unit) {
        Scaffold (floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(CandidateEvent.OpenDialog) }) {
               Icon(
                   imageVector = Icons.Default.Add,
                   contentDescription = "Create Resume"
               )
            }

        }) { padding ->

            if (state.isAddingCandidate) {
                CandidateDialog(state = state, onEvent = onEvent)
            }
            LazyColumn(contentPadding = padding,
                modifier = Modifier.fillMaxSize().background(color = Color.LightGray),
                verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(state.candidates, key = { it.id}) { candidate ->
                        Column(modifier = Modifier.padding(15.dp).background(color = Color.White, shape = RoundedCornerShape(15.dp)).border(
                            BorderStroke(1.dp, Color.DarkGray), shape = RoundedCornerShape(15.dp)
                        ).padding(15.dp))
                         {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Resume", fontWeight = FontWeight.Bold)
                                Spacer(Modifier.weight(1f))
                                IconButton(onClick = { onEvent(CandidateEvent.OpenDialog) })
                                {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Edit Resume"
                                    )
                                }
                                IconButton(onClick = {
                                    onEvent(CandidateEvent.deleteCandidate(candidate))

                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete Resume"
                                    )
                                }
                            }
                            Column(modifier = Modifier) {
                                Card(modifier = Modifier.fillMaxSize()) {
                                    Column(Modifier.padding(15.dp)) {
                                        Text(
                                            text = "Full name: ${candidate.candidate_info?.name}"
                                        )
                                        Text(
                                            text = "Profession: ${candidate.candidate_info?.profession}"
                                        )
                                        Text(
                                            text = "Sex: ${candidate.candidate_info?.sex}"
                                        )
                                        Text(
                                            text = "Date of birth: ${candidate.candidate_info?.birth_date}"
                                        )
                                        candidate.candidate_info?.contacts?.email.let {
                                            Text(
                                                text = "Email: $it"
                                            )
                                        }
                                        candidate.candidate_info?.contacts?.phone.let {
                                            Text(
                                                text = "Phone: $it"
                                            )
                                        }
                                        Text(
                                            text = "Relocation: ${candidate.candidate_info?.relocation}"
                                        )
                                    }
                                }

                                candidate.education?.let { educationList ->
                                    Spacer(modifier = Modifier.size(20.dp))
                                    Text(text = "Education", fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.size(20.dp))
                                    educationList.forEach { education ->
                                        education?.let {
                                            Card(modifier = Modifier.fillMaxSize()) {
                                                Column(Modifier.padding(15.dp)) {
                                                    Text(text = "Type: ${it.type}")
                                                    Text(text = "Started: ${it.year_start}")
                                                    Text(text = "Graduated: ${it.year_end}")
                                                    Text(text = "Description: ${it.description}")
                                                }
                                            }
                                            Spacer(modifier = Modifier.size(20.dp))
                                        }
                                    }
                                }

                                candidate.job_experience?.let { jobList ->
                                    Spacer(modifier = Modifier.size(20.dp))
                                    Text(text = "Experience", fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.size(20.dp))
                                    jobList.forEach { job ->
                                        job?.let {
                                            Card(modifier = Modifier.fillMaxSize()) {
                                                Column(Modifier.padding(15.dp)) {
                                                    Text(text = "Company: ${it.company_name}")
                                                    Text(text = "From: ${it.date_start}")
                                                    Text(text = "To: ${it.date_end}")
                                                    Text(text = "Description: ${it.description}")
                                                }
                                            }
                                            Spacer(modifier = Modifier.size(20.dp))
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.size(20.dp))
                                Text(text = "Additional Info", fontWeight = FontWeight.Bold)
                                candidate.free_form?.let {
                                    Text(
                                        text = it
                                    )
                                }
                            }


                        }
                    }
                }

        }
    }
