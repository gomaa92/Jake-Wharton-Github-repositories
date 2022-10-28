package com.example.jakewhartongithubrepositories.feature.listrepositories.domain.repository

import com.example.jakewhartongithubrepositories.core.data.remote.NetworkResponse
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity

interface ListRepositoriesRepository {
    suspend fun getRepositories(page: Int): NetworkResponse<List<RepositoryEntity>>
}