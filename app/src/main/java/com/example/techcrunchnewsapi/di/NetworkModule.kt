package com.example.techcrunchnewsapi.di

import com.example.techcrunchnewsapi.business.TechCrunchRemoteDS
import com.example.techcrunchnewsapi.business.TechCrunchRepository
import com.example.techcrunchnewsapi.framework.TechCrunchRemoteDSImpl
import com.example.techcrunchnewsapi.framework.TechCrunchRepositoryImpl
import com.example.techcrunchnewsapi.network.ApiClient
import com.example.techcrunchnewsapi.network.ApiService
import dagger.Module
import dagger.Provides

@Module
object NetworkModule {
    @Provides
    @JvmStatic
    internal fun provideApiClient(): ApiService {
        return ApiClient.getApiService()
    }

    @Provides
    @JvmStatic
    internal fun provideRemoteDS(api: ApiService) : TechCrunchRemoteDS {
        return TechCrunchRemoteDSImpl(api)
    }

    @Provides
    @JvmStatic
    internal fun provideTechCrunchRepo(remoteDS: TechCrunchRemoteDS) : TechCrunchRepository {
        return TechCrunchRepositoryImpl(remoteDS)
    }
}