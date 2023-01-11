package com.example.techcrunchnewsapi.network

import com.example.techcrunchnewsapi.business.models.TechCrunch
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


//https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=3af8c839c96c48268664d920ef863daf
// Temporarily hardcoded
interface ApiService {
    @GET("top-headlines")
    fun getNewsArticles(@Query("sources") sources: String = "techcrunch", @Query("apiKey") apiKey: String = "3af8c839c96c48268664d920ef863daf"): Single<TechCrunch>
}