package com.example.jakewhartongithubrepositories.feature.listrepositories.di

import com.example.jakewhartongithubrepositories.feature.listrepositories.data.remote.ListRepositoryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
class ApiModule {
    @Provides
    fun provideMessagesApi(retrofit: Retrofit): ListRepositoryService {
        return retrofit.create(ListRepositoryService::class.java)
    }
}