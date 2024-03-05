package com.example.repository


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//interface CandidateRepository {
//
//    suspend fun getAllCandidates() : Flow<List<Candidate>>
//    suspend fun getCandidate(id : Int) : Flow<Candidate?>
//    suspend fun deleteCandidate(candidate: Candidate)
//    suspend fun insertCandidate(candidate : Candidate)
//    suspend fun updateCandidate(candidate: Candidate)
//}
//
//class CandidateRepositoryImpl @Inject constructor(private val localDataSource: com.example.myapplication.CandidateDao) : CandidateRepository {
//    override suspend fun getAllCandidates(): Flow<List<Candidate>> = localDataSource.getCandidates()
//    override suspend fun updateCandidate(candidate: Candidate) = localDataSource.insertCandidate(candidate)
//    override suspend fun insertCandidate(candidate: Candidate) = localDataSource.insertCandidate(candidate)
//    override suspend fun deleteCandidate(candidate: Candidate) = localDataSource.deleteCandidate(candidate)
//    override suspend fun getCandidate(id: Int): Flow<Candidate?> = localDataSource.getCandidate(id)
//
//}

