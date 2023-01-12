package com.example.techcrunchnewsapi.business.models

import kotlinx.coroutines.flow.Flow

interface TechCrunchRepository {
    suspend fun getNewsArticles(): Flow<TechCrunch>
}