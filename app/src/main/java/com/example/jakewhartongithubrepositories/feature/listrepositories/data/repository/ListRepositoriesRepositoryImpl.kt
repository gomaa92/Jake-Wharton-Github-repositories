package com.example.jakewhartongithubrepositories.feature.listrepositories.data.repository

import android.content.Context
import com.example.jakewhartongithubrepositories.core.data.remote.NetworkResponse
import com.example.jakewhartongithubrepositories.core.utils.NetworkManager
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.local.ListRepositoryLocalDataSource
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.remote.ListRepositoryRemoteDataSource
import com.example.jakewhartongithubrepositories.feature.listrepositories.domain.repository.ListRepositoriesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ListRepositoriesRepositoryImpl @Inject constructor(
    private val remoteDataSource: ListRepositoryRemoteDataSource,
    private val localDataSource: ListRepositoryLocalDataSource,
    @ApplicationContext private val context: Context
) : ListRepositoriesRepository {
    override suspend fun getRepositories(page: Int): NetworkResponse<List<RepositoryEntity>> {
        val isOnline = NetworkManager.isOnline(context)
        return if (isOnline) {
            getRepositoriesFromRemote(page)
        } else {
            getRepositoriesFromDB(page)
        }
    }

    private suspend fun getRepositoriesFromRemote(page: Int): NetworkResponse<List<RepositoryEntity>> {
        val result = remoteDataSource.getRepositories(page)
        if (result is NetworkResponse.Success) {
            localDataSource.insertRepository(repositories = result.data)
        }
        return remoteDataSource.getRepositories(page)
    }

    private suspend fun getRepositoriesFromDB(page: Int): NetworkResponse<List<RepositoryEntity>> {
        return NetworkResponse.SuccessLocal(localDataSource.getAllRepository(page))
    }
}