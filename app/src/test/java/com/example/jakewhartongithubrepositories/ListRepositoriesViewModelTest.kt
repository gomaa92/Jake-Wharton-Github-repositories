package com.example.jakewhartongithubrepositories

import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity
import com.example.jakewhartongithubrepositories.feature.listrepositories.domain.usecase.ListRepositoriesUseCase
import com.example.jakewhartongithubrepositories.feature.listrepositories.presentation.viewmodel.ListRepositoriesContract.*
import com.example.jakewhartongithubrepositories.feature.listrepositories.presentation.viewmodel.ListRepositoriesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.time.ExperimentalTime

@ExperimentalTime
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class ListRepositoriesViewModelTest {


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var viewModel: ListRepositoriesViewModel

    @Mock
    lateinit var useCase: ListRepositoriesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = ListRepositoriesViewModel(
            useCase
        )
    }

    @ExperimentalTime
    @Test
    fun getRepositoriesReturnSuccess() {
        runBlocking {
            val list = arrayListOf<RepositoryEntity>()
            val action = ListRepositoriesActions.GetRepositories
            val result = ListRepositoriesResult.Success(list)
            whenever(useCase.execute(1)).thenReturn(result)
            viewModel.dispatch(action)

            Assert.assertEquals(
                viewModel.uiState.first(), ListRepositoriesViewState.Success(
                    list
                )
            )
        }
    }

    @ExperimentalTime
    @Test
    fun getRepositoriesReturnLocalSuccess() {
        runBlocking {
            val list = arrayListOf<RepositoryEntity>()
            val action = ListRepositoriesActions.GetRepositories
            val result = ListRepositoriesResult.SuccessLocal(list)
            whenever(useCase.execute(1)).thenReturn(result)
            viewModel.dispatch(action)

            Assert.assertEquals(
                viewModel.uiState.first(), ListRepositoriesViewState.SuccessLocal(
                    list
                )
            )
        }
    }

    @ExperimentalTime
    @Test
    fun getRepositoriesReturnFailure() {
        val exception = Exception()
        runBlocking {
            val action = ListRepositoriesActions.GetRepositories
            val result = ListRepositoriesResult.Failure(exception)
            whenever(useCase.execute(1)).thenReturn(result)
            viewModel.dispatch(action)

            Assert.assertEquals(
                viewModel.uiEvent.first(), ListRepositoriesViewEvent.Failure(
                    exception
                )
            )
        }
    }
}