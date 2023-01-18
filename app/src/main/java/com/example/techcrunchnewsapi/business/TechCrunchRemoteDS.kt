package com.example.techcrunchnewsapi.business

import com.example.techcrunchnewsapi.business.models.TechCrunch
import retrofit2.Response

interface TechCrunchRemoteDS {
    suspend fun getNewsArticles(): Response<TechCrunch>
}