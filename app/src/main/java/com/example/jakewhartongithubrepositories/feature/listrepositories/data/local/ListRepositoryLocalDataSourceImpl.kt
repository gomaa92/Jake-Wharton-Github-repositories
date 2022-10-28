package com.example.jakewhartongithubrepositories.feature.listrepositories.data.local

import com.example.jakewhartongithubrepositories.core.data.local.RepositoryDao
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity
import javax.inject.Inject

class ListRepositoryLocalDataSourceImpl @Inject constructor(private val repositoryDao: RepositoryDao) :
    ListRepositoryLocalDataSource {
    override suspend fun insertRepository(repositories: List<RepositoryEntity>) {
        repositoryDao.insertAll(repositories)
    }

    override suspend fun getAllRepository(page: Int): List<RepositoryEntity> {
        return repositoryDao.getAllRepositories()
    }
}