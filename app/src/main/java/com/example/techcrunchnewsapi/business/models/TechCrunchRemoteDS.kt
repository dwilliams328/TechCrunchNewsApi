package com.example.techcrunchnewsapi.business.models

import retrofit2.Response

interface TechCrunchRemoteDS {
    suspend fun getNewsArticles(): Response<TechCrunch>
}