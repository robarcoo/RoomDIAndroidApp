package com.example.repository


import android.content.Context
import android.widget.Toast
import com.example.model.Network
import com.example.retrofit.CandidateApi
import dao.CandidateDao
import entity.Candidate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CandidateRepository {


    suspend fun loadCandidates() : Flow<List<Candidate>>
suspend fun loadCandidatesOffline(): Flow<List<Candidate>>

    suspend fun insertCandidate(candidate : Candidate) : Boolean

    suspend fun deleteCandidate(candidate : Candidate) : Boolean

    suspend fun getLastId() : Long

    suspend fun editCandidate(candidate: Candidate, id: Int) : Boolean
}

class CandidateRepositoryImpl @Inject constructor(private val dao : CandidateDao, private val network: Network, private val context : Context) :
    CandidateRepository {
    override suspend fun loadCandidates() : Flow<List<Candidate>> {
        val candidateApi : CandidateApi = network.getRetrofit().create(CandidateApi::class.java)
        val candidates = candidateApi.getAllCandidates()

        candidates.forEach {
                dao.insertCandidate(it)
        }
        return dao.getCandidates()
    }

    override suspend fun loadCandidatesOffline() : Flow<List<Candidate>> {
        return dao.getCandidates()
    }

    override suspend fun getLastId(): Long {

        return dao.getLastId().last()
    }

    override suspend fun deleteCandidate(candidate : Candidate) : Boolean {
        val candidateApi : CandidateApi = network.getRetrofit().create(CandidateApi::class.java)
        return try {
            candidateApi.deleteCandidate(candidate.id)
            dao.deleteCandidate(candidate)
            true
        } catch (e : Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Can't make changes when offline.", Toast.LENGTH_SHORT).show()

            }
            false
        }

    }

    override suspend fun insertCandidate(candidate : Candidate) : Boolean {
        val candidateApi : CandidateApi = network.getRetrofit().create(CandidateApi::class.java)
        return try {
            candidateApi.insertCandidate(candidate)
            dao.insertCandidate(candidate)
            true
        } catch (e : Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Can't make changes when offline.", Toast.LENGTH_SHORT).show()

            }
            false
        }
    }

    override suspend fun editCandidate(candidate : Candidate, id: Int) : Boolean {
        val candidateApi : CandidateApi = network.getRetrofit().create(CandidateApi::class.java)
        return try {
            candidateApi.editCandidate(id, candidate)
            dao.insertCandidate(candidate)
            true
        } catch (e : Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Can't make changes when offline.", Toast.LENGTH_SHORT).show()

            }
            false
        }
    }

}

