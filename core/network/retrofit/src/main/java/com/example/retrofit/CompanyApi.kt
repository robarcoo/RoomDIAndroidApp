package com.example.retrofit
import com.example.model.Company
import retrofit2.http.GET
import retrofit2.http.Path

interface CompanyApi {
    @GET("company")
    suspend fun getAllCompanies() : List<Company>

    @GET("company/{id}")
    suspend fun getAllCompanyById(@Path("id") id : Int) : Company
}