package com.example.jakewhartongithubrepositories.feature.listrepositories.presentation.viewmodel

import com.example.jakewhartongithubrepositories.core.persentation.viewmodel.Action
import com.example.jakewhartongithubrepositories.core.persentation.viewmodel.BaseResult
import com.example.jakewhartongithubrepositories.core.persentation.viewmodel.ViewEvent
import com.example.jakewhartongithubrepositories.core.persentation.viewmodel.ViewState
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity

interface ListRepositoriesContract {
    sealed class ListRepositoriesActions : Action {
        object GetRepositories : ListRepositoriesActions()
    }

    sealed class ListRepositoriesResult : BaseResult {
        data class Failure(val exception: Exception) : ListRepositoriesResult()
        data class Success(val repositories: List<RepositoryEntity>) : ListRepositoriesResult()
        data class SuccessLocal(val repositories: List<RepositoryEntity>) : ListRepositoriesResult()
    }

    sealed class ListRepositoriesViewState : ViewState {
        data class Loading(val isLoading: Boolean = true) : ListRepositoriesViewState()
        data class Success(val repositories: List<RepositoryEntity>) : ListRepositoriesViewState()
        data class SuccessLocal(val repositories: List<RepositoryEntity>) : ListRepositoriesViewState()
    }

    sealed class ListRepositoriesViewEvent : ViewEvent {
        data class Failure(val exception: Exception) : ListRepositoriesViewEvent()
    }
}