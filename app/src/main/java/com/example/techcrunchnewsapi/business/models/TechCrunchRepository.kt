package com.example.techcrunchnewsapi.business.models

import io.reactivex.Observable
import io.reactivex.Single

interface TechCrunchRepository {
    fun getNewsArticles(): Single<TechCrunch>
}