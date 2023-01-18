package com.example.techcrunchnewsapi.framework

import com.example.techcrunchnewsapi.business.models.TechCrunch
import com.example.techcrunchnewsapi.business.TechCrunchRemoteDS
import com.example.techcrunchnewsapi.business.TechCrunchRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TechCrunchRepositoryImpl(private val remoteDS: TechCrunchRemoteDS): TechCrunchRepository {

    override suspend fun getNewsArticles(): Flow<TechCrunch> {
        // revisit for `Single -> Flow` conversion
        val response = remoteDS.getNewsArticles()
            return flow {
                response.body()?.let { emit(it) }
            }
        }
}