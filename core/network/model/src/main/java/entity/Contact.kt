package entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact (
    @ColumnInfo(name="candidate_id")
    val candidate_id: Int?,
    val phone: String?,
    val email: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)