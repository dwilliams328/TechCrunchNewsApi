package com.example.techcrunchnewsapi.framework

import com.example.techcrunchnewsapi.business.models.TechCrunch
import com.example.techcrunchnewsapi.business.models.TechCrunchRemoteDS
import com.example.techcrunchnewsapi.business.models.TechCrunchRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TechCrunchRepositoryImpl(private val remoteDS: TechCrunchRemoteDS): TechCrunchRepository {
    override fun getNewsArticles(): Single<TechCrunch> {
        return remoteDS.getNewsArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}