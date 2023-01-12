package com.example.techcrunchnewsapi.framework

import com.example.techcrunchnewsapi.business.models.TechCrunch
import com.example.techcrunchnewsapi.business.models.TechCrunchRemoteDS
import com.example.techcrunchnewsapi.network.ApiService
import retrofit2.Response

class TechCrunchRemoteDSImpl(private val api: ApiService) : TechCrunchRemoteDS {

    override suspend fun getNewsArticles(): Response<TechCrunch> {
        return api.getNewsArticles()
    }
}