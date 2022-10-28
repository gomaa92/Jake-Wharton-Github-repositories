package com.example.jakewhartongithubrepositories.feature.listrepositories.data.remote

import com.example.jakewhartongithubrepositories.core.data.remote.NetworkResponse
import com.example.jakewhartongithubrepositories.core.data.remote.NetworkServiceCall
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity

interface ListRepositoryRemoteDataSource :NetworkServiceCall{
    suspend fun getRepositories(page: Int): NetworkResponse<List<RepositoryEntity>>
}