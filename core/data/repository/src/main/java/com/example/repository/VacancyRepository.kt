package com.example.repository

import com.example.model.FullVacancy
import com.example.model.Network
import com.example.model.Vacancy
import com.example.retrofit.VacancyApi

interface VacancyRepository {
    suspend fun getAllVacancies() : List<Vacancy>

    suspend fun getVacancyById(id : Int) : FullVacancy
}

class VacancyRepositoryImpl(private val network : Network) : VacancyRepository {
    override suspend fun getAllVacancies(): List<Vacancy> {
        val vacancyApi = network.getRetrofit().create(VacancyApi::class.java)
        return vacancyApi.getAllVacancies()
    }

    override suspend fun getVacancyById(id : Int): FullVacancy {
        val vacancyApi = network.getRetrofit().create(VacancyApi::class.java)
        return vacancyApi.getVacancyById(id)
    }

}