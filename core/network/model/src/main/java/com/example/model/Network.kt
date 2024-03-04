package com.example.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject


class Network @Inject constructor(private val okHttpClient: OkHttpClient, private val retrofit : Retrofit) {

    fun getRetrofit() = retrofit
}