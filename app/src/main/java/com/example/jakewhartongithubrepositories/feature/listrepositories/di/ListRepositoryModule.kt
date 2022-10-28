package com.example.jakewhartongithubrepositories.feature.listrepositories.di


import com.example.jakewhartongithubrepositories.feature.listrepositories.data.local.ListRepositoryLocalDataSource
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.local.ListRepositoryLocalDataSourceImpl
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.remote.ListRepositoryRemoteDataSource
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.remote.ListRepositoryRemoteDataSourceImpl
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.repository.ListRepositoriesRepositoryImpl
import com.example.jakewhartongithubrepositories.feature.listrepositories.domain.repository.ListRepositoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class ListRepositoryModule {

    @Binds
    abstract fun bindRemoteDataSourceModel(
        remoteDataSource: ListRepositoryRemoteDataSourceImpl
    ): ListRepositoryRemoteDataSource

    @Binds
    abstract fun bindLocalDataSourceModel(
        remoteDataSource: ListRepositoryLocalDataSourceImpl
    ): ListRepositoryLocalDataSource

    @Binds
    abstract fun bindRepository(
        repository: ListRepositoriesRepositoryImpl
    ): ListRepositoriesRepository
}