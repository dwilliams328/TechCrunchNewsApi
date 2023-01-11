package com.example.techcrunchnewsapi.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.techcrunchnewsapi.business.models.NewsArticle
import com.example.techcrunchnewsapi.business.models.TechCrunch
import com.example.techcrunchnewsapi.business.models.TechCrunchRepository
import com.example.techcrunchnewsapi.di.TechCrunchInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel : ViewModel() {
    init {
        TechCrunchInjector.init()
        TechCrunchInjector.getComponent().inject(this)
        getNewsArticles()
    }

    @Inject
    lateinit var repository: TechCrunchRepository
    private var _newArticle: MutableLiveData<List<NewsArticle>> = MutableLiveData<List<NewsArticle>>() // Use dto-> converter -> uiModel.. also expose Live Data not mutable
    var newsArticle: LiveData<List<NewsArticle>> = _newArticle

    private lateinit var disposable: Disposable

    private fun getNewsArticles() {
        disposable = repository.getNewsArticles().subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<TechCrunch>() {
                override fun onSuccess(t: TechCrunch) {
                    _newArticle.postValue(t.articles)
                }

                override fun onError(e: Throwable) {
                    Log.d("abc", "look here for error")
                    _newArticle.postValue(mutableListOf(
                        NewsArticle(
                            "David",
                            "Title4",
                            "desc of title4",
                            "https://techcrunch.com/wp-content/uploads/2022/04/tiktok-header.webp"
                        )))
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}