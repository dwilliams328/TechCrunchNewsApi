package com.example.techcrunchnewsapi.framework

import com.example.techcrunchnewsapi.business.models.TechCrunch
import com.example.techcrunchnewsapi.business.models.TechCrunchRemoteDS
import com.example.techcrunchnewsapi.network.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TechCrunchRemoteDSImpl(private val api: ApiService) : TechCrunchRemoteDS {
    override fun getNewsArticles(): Single<TechCrunch> {
        return api.getNewsArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}