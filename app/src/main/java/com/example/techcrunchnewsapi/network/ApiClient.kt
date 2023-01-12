package com.example.techcrunchnewsapi.network


import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    private fun getRetrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(MoshiConverterFactory.create()).build()
    }

    fun getApiService(): ApiService {
        return getRetrofitBuilder().create(ApiService::class.java)
    }
}