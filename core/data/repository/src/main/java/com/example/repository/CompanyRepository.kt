package com.example.repository

import com.example.model.Company
import com.example.model.Network
import com.example.retrofit.CompanyApi
import retrofit2.create

interface CompanyRepository {
    suspend fun getAllCompanies() : List<Company> = emptyList()

    suspend fun getCompany(id : Int) : Company
}

class CompanyRepositoryImpl(private val network: Network) : CompanyRepository {
    override suspend fun getAllCompanies(): List<Company> {
        val companyApi = network.getRetrofit().create(CompanyApi::class.java)
        return companyApi.getAllCompanies()
    }

    override suspend fun getCompany(id : Int): Company {
        val companyApi = network.getRetrofit().create(CompanyApi::class.java)
        return companyApi.getAllCompanyById(id)
    }
}



