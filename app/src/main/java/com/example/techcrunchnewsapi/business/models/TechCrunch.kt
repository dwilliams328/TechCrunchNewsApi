package com.example.techcrunchnewsapi.business.models
import java.io.Serializable

data class TechCrunch(
    val status: String?,
    val totalResults: Int?,
    val articles: List<NewsArticle>
)

data class NewsArticle(
//    val source: SourceItem,
    val author: String,
    val title: String,
    val description: String,
//    val url: String?,
    val urlToImage: String,
//    val publishedAt: String,
//    val content: String
) : Serializable // for passing as bundle

data class SourceItem(
    val id: String,
    val name: String
)
