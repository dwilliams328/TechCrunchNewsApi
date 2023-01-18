package com.example.techcrunchnewsapi.business

import com.example.techcrunchnewsapi.business.models.TechCrunch
import kotlinx.coroutines.flow.Flow

interface TechCrunchRepository {
    suspend fun getNewsArticles(): Flow<TechCrunch>
}