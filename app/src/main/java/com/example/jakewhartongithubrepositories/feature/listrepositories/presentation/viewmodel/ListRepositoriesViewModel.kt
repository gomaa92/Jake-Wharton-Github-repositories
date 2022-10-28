package com.example.jakewhartongithubrepositories.feature.listrepositories.presentation.viewmodel

import com.example.basemvi.core.persentation.viewmodel.BaseViewModel
import com.example.jakewhartongithubrepositories.feature.listrepositories.domain.usecase.ListRepositoriesUseCase
import com.example.jakewhartongithubrepositories.feature.listrepositories.presentation.viewmodel.ListRepositoriesContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class ListRepositoriesViewModel @Inject constructor(private val useCase: ListRepositoriesUseCase) :
    BaseViewModel<ListRepositoriesViewState, ListRepositoriesViewEvent, ListRepositoriesActions, ListRepositoriesResult>(
        ListRepositoriesViewState.Loading()
    ) {
    init {
        dispatch(ListRepositoriesActions.GetRepositories)
    }

    private var page = 1
    override fun handle(action: ListRepositoriesActions): Flow<ListRepositoriesResult> = flow {
        when (action) {
            ListRepositoriesActions.GetRepositories -> {
                when (val result = useCase.execute(page++)) {
                    is ListRepositoriesResult.Failure -> onViewEvent(
                        ListRepositoriesViewEvent.Failure(
                            result.exception
                        )
                    )
                    else -> emit(result)
                }
            }
        }
    }

    override fun reduce(result: ListRepositoriesResult): ListRepositoriesViewState {
        return when (result) {
            is ListRepositoriesResult.Success -> {
                ListRepositoriesViewState.Success(repositories = result.repositories)
            }
            is ListRepositoriesResult.SuccessLocal -> {
                ListRepositoriesViewState.SuccessLocal(repositories = result.repositories)
            }
            is ListRepositoriesResult.Failure -> currentState
        }
    }
}