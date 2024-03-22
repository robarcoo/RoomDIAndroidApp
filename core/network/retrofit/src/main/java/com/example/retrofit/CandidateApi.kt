package com.example.retrofit

import entity.Candidate
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface CandidateApi {

    @GET("candidate")
    suspend fun getAllCandidates(): List<Candidate>

    @GET("candidate/{id}")
    suspend fun getCandidate(@Path("id") id: Int) : Candidate

    @POST("candidate/{id}/education")
    suspend fun addEducation(@Path("id") id: Int)

    @POST("candidate/{id}/experience")
    suspend fun addExperience(@Path("id") id: Int)
    @PATCH("candidate/{id}")
    suspend fun editCandidate(@Path("id") id: Int, @Body candidate: Candidate)

    @DELETE("candidate/{id}")
    suspend fun deleteCandidate(@Path("id") id: Int) : Candidate

    @POST("candidate")
    suspend fun insertCandidate(@Body candidate: Candidate) : Int

}