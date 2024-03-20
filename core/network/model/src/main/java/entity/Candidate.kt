package entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Candidate(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo( name = "candidate_info")
    val candidate_info: CandidateInfo?,
    val education: List<Education>,
    @ColumnInfo(name="job_experience")
    val job_experience: List<Experience?>?,
    @ColumnInfo(name="free_form")
    val free_form: String?,


    )