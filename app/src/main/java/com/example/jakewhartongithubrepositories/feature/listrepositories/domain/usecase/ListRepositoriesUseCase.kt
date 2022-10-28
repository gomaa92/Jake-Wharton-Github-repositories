package com.example.jakewhartongithubrepositories.feature.listrepositories.domain.usecase

import com.example.jakewhartongithubrepositories.core.data.remote.NetworkResponse
import com.example.jakewhartongithubrepositories.core.domain.usecase.SuspendableUseCase
import com.example.jakewhartongithubrepositories.feature.listrepositories.domain.repository.ListRepositoriesRepository
import com.example.jakewhartongithubrepositories.feature.listrepositories.presentation.viewmodel.ListRepositoriesContract.ListRepositoriesResult
import javax.inject.Inject

class ListRepositoriesUseCase @Inject constructor(private val repository: ListRepositoriesRepository) :
    SuspendableUseCase<Int, ListRepositoriesResult> {
    override suspend fun execute(input: Int): ListRepositoriesResult {
        return when (val result = repository.getRepositories(input)) {
            is NetworkResponse.Failure -> ListRepositoriesResult.Failure(result.exception)
            is NetworkResponse.Success -> ListRepositoriesResult.Success(result.data)
            is NetworkResponse.SuccessLocal -> ListRepositoriesResult.SuccessLocal(result.data)
        }
    }
}