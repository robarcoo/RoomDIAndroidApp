package entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CandidateInfo (
    @ColumnInfo(name="candidate_id")
    var candidate_id: Int,
    val name: String?,
    val profession: String?,
    val sex: String?,
    @ColumnInfo(name="birth_date")
    val birth_date: String?,
    val contacts: Contact?,
    val relocation: String?,
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
)