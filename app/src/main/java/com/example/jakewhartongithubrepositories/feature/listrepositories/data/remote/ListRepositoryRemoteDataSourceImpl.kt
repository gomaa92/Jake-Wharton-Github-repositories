package com.example.jakewhartongithubrepositories.feature.listrepositories.data.remote

import com.example.jakewhartongithubrepositories.core.data.remote.NetworkResponse
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity
import javax.inject.Inject

class ListRepositoryRemoteDataSourceImpl @Inject constructor(private val service: ListRepositoryService) :
    ListRepositoryRemoteDataSource {
    override suspend fun getRepositories(page: Int): NetworkResponse<List<RepositoryEntity>> {
        return safeApiCall { service.getRepositories(page = page) }
    }
}