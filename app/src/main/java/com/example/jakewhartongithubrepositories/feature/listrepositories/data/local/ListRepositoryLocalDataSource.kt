package com.example.jakewhartongithubrepositories.feature.listrepositories.data.local

import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity

interface ListRepositoryLocalDataSource {
    suspend fun insertRepository(repositories: List<RepositoryEntity>)
    suspend fun getAllRepository(): List<RepositoryEntity>
}