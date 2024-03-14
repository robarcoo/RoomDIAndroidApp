package entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Education(
    @ColumnInfo(name="candidate_id")
    val candidate_id: Int = 0,
    val type: String?,
    @ColumnInfo(name="year_start")
    val year_start: String?,
    @ColumnInfo(name="year_finish")
    val year_finish: String?,
    val description: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)