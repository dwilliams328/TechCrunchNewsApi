package com.example.techcrunchnewsapi.ui.stateholders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techcrunchnewsapi.business.models.NewsArticle
import com.example.techcrunchnewsapi.business.TechCrunchRepository
import com.example.techcrunchnewsapi.di.TechCrunchInjector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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

    private var _newsArticle: MutableLiveData<MutableList<NewsArticle>> = MutableLiveData()
    var newsArticle: LiveData<MutableList<NewsArticle>> = _newsArticle // TODO dto -> converter -> uiModel

    private fun getNewsArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            val async1 = async { repository.getNewsArticles() }
            async1.await().let { techCrunchFlow ->
                techCrunchFlow.collect {
                    _newsArticle.postValue(it.articles.toMutableList())
                }
            }
        }
    }

    fun removeArticleAt(position: Int) {
        // TODO How to perform transformations to mutable live data?
        _newsArticle.postValue(_newsArticle.value?.toMutableList()?.apply {
            removeAt(position)
        })

    }

    fun addArticleAt(position: Int, item: NewsArticle) {
        _newsArticle.postValue(_newsArticle.value?.toMutableList()?.apply {
            add(position, item)
        })

    }
}