package com.example.techcrunchnewsapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techcrunchnewsapi.business.models.NewsArticle
import com.example.techcrunchnewsapi.business.models.TechCrunchRepository
import com.example.techcrunchnewsapi.di.TechCrunchInjector
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : ViewModel() {
    init {
        TechCrunchInjector.init()
        TechCrunchInjector.getComponent().inject(this)
        getNewsArticles()
    }

    @Inject
    lateinit var repository: TechCrunchRepository
    private var _newArticle: MutableStateFlow<List<NewsArticle>> = MutableStateFlow(
                                mutableListOf(NewsArticle(
                            "David",
                            "Title4",
                            "desc of title4",
                            "https://techcrunch.com/wp-content/uploads/2022/04/tiktok-header.webp"
                        ))
    ) // Use dto-> converter -> uiModel.. also expose Live Data not mutable
    var newsArticle: StateFlow<List<NewsArticle>> = _newArticle.asStateFlow()

    private fun getNewsArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            val async1 = async { repository.getNewsArticles() }
            async1.await().let { techCrunchFlow ->
                techCrunchFlow.collect {
                    _newArticle.value = it.articles
                }
            }
        }
    }
}