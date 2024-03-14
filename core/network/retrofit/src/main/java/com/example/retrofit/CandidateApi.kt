package com.example.retrofit

import entity.Candidate
import retrofit2.http.GET
import retrofit2.http.Path

interface CandidateApi {

    @GET("candidate")
    suspend fun getAllCandidates(): List<Candidate>

    @GET("candidate/{id}")
    suspend fun getCandidate(@Path("id") id: Int) : Candidate

}