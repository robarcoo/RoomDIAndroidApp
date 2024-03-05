package com.example.repository


import com.example.database.dao.CandidateDao
import com.example.database.entity.Candidate
import com.example.model.Network
import com.example.retrofit.CandidateApi
import com.example.retrofit.CompanyApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CandidateRepository {

    suspend fun loadFromServer() : List<Candidate>
}

class CandidateRepositoryImpl (private val network: Network) : CandidateRepository {
    override suspend fun loadFromServer() : List<Candidate> {
        val companyApi = network.getRetrofit().create(CandidateApi::class.java)
        return companyApi.getAllCandidates()
    }

}

