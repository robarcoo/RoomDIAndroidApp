package entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Experience(
    @ColumnInfo(name="candidate_id")
    val candidate_id: Int?,
    @ColumnInfo(name="date_start")
    val date_start: String?,
    @ColumnInfo(name="date_end")
    val date_end: String?,
    @ColumnInfo(name="company_name")
    val company_name: String?,
    val description: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)