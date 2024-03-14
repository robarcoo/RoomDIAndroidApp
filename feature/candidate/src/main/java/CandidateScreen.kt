
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.dialog.CandidateDialog
import com.example.common.event.CandidateEvent
import com.example.common.viewmodel.CandidateState

@Composable
fun CandidateScreen(state: CandidateState, onEvent: (CandidateEvent) -> Unit) {
        Scaffold (floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(CandidateEvent.OpenDialog) }) {
               Icon(
                   imageVector = Icons.Default.Edit,
                   contentDescription = "Edit Candidate"
               )
            }

        }) { padding ->

            if (state.isAddingCandidate) {
                CandidateDialog(state = state, onEvent = onEvent)
            }
            LazyColumn(contentPadding = padding,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(state.candidates, key = { it.id}) { candidate ->
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "${candidate.candidate_info}"
                                )
                                Text(
                                    text = "${candidate.education}"
                                )
                                Text(
                                    text = "${candidate.job_experience}"
                                )
                                Text(
                                    text = "${candidate.free_form}"
                                )
                            }
                            IconButton(onClick = {
                                onEvent(CandidateEvent.deleteCandidate(candidate))

                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Candidate"
                                )
                            }


                        }
                }

        }
    }
}