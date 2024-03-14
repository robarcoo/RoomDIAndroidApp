package dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import entity.Candidate
import kotlinx.coroutines.flow.Flow

@Dao
interface CandidateDao {

    @Upsert
    suspend fun insertCandidate(candidate: Candidate)


    @Delete
    suspend fun deleteCandidate(candidate: Candidate)


    @Query("SELECT * FROM Candidate")
    fun getCandidates() : Flow<List<Candidate>>


    @Query("SELECT * FROM Candidate WHERE Candidate.id == (:id)")
    fun getCandidate(id: Int) : Flow<Candidate?>
}