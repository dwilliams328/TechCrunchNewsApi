package com.example.techcrunchnewsapi.business.models

import io.reactivex.Observable
import io.reactivex.Single

interface TechCrunchRemoteDS {
    fun getNewsArticles(): Single<TechCrunch>
}