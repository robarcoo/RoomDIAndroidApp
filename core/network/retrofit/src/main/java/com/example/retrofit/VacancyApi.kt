package com.example.retrofit

import com.example.model.FullVacancy
import com.example.model.Vacancy
import retrofit2.http.GET
import retrofit2.http.Path

interface VacancyApi {
    @GET("vacancy")
    suspend fun getAllVacancies() : List<Vacancy>
    @GET("vacancy/{id}")
    suspend fun getVacancyById(@Path("id") id : Int) : FullVacancy
}