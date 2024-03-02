package com.example.repository


import com.example.database.entity.Candidate
import com.example.database.local.LocalDataSource
import kotlinx.coroutines.flow.Flow

interface CandidateRepository {

    suspend fun getAllCandidates() : Flow<List<Candidate>>
    suspend fun getCandidate(id : Int) : Flow<Candidate?>
    suspend fun deleteCandidate(candidate: Candidate)
    suspend fun insertCandidate(candidate : Candidate)
    suspend fun updateCandidate(candidate: Candidate)
}

class CandidateRepositoryImpl(private val localDataSource: LocalDataSource) : CandidateRepository {
    override suspend fun getAllCandidates(): Flow<List<Candidate>> = localDataSource.getCandidates()
    override suspend fun updateCandidate(candidate: Candidate) = localDataSource.insertCandidate(candidate)
    override suspend fun insertCandidate(candidate: Candidate) = localDataSource.insertCandidate(candidate)
    override suspend fun deleteCandidate(candidate: Candidate) = localDataSource.deleteCandidate(candidate)
    override suspend fun getCandidate(id: Int): Flow<Candidate?> = localDataSource.getCandidate(id)

}

