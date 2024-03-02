package com.example.database.local

import com.example.database.dao.CandidateDao
import com.example.database.entity.Candidate
import dagger.Component
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


class LocalDataSource (
    private val candidateDao: CandidateDao
) {
    fun getCandidates() : Flow<List<Candidate>> = candidateDao.getCandidates()

    suspend fun insertCandidate(candidate: Candidate) = candidateDao.insertCandidate(candidate)

    suspend fun deleteCandidate(candidate: Candidate) = candidateDao.deleteCandidate(candidate)

    fun getCandidate(id : Int) : Flow<Candidate?> = candidateDao.getCandidate(id)
}