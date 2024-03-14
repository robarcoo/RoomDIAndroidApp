package com.example.repository


import com.example.model.Network
import com.example.retrofit.CandidateApi
import dao.CandidateDao
import entity.Candidate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CandidateRepository {

    suspend fun loadCandidates() : Flow<List<Candidate>>

    suspend fun insertCandidate(candidate : Candidate)

    suspend fun deleteCandidate(candidate : Candidate)
}

class CandidateRepositoryImpl @Inject constructor(private val dao : CandidateDao, private val network: Network) :
    CandidateRepository {
    override suspend fun loadCandidates() : Flow<List<Candidate>> {
        val companyApi = network.getRetrofit().create(CandidateApi::class.java)
        companyApi.getAllCandidates().forEach {
            dao.insertCandidate(it)
        }

        return dao.getCandidates()
    }

    override suspend fun deleteCandidate(candidate : Candidate) {
        dao.deleteCandidate(candidate)
    }

    override suspend fun insertCandidate(candidate : Candidate) {
        dao.insertCandidate(candidate)
    }

}

