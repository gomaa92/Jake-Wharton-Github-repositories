package com.example.jakewhartongithubrepositories

import com.example.jakewhartongithubrepositories.core.data.remote.NetworkResponse
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity
import com.example.jakewhartongithubrepositories.feature.listrepositories.domain.repository.ListRepositoriesRepository
import com.example.jakewhartongithubrepositories.feature.listrepositories.domain.usecase.ListRepositoriesUseCase
import com.example.jakewhartongithubrepositories.feature.listrepositories.presentation.viewmodel.ListRepositoriesContract
import kotlinx.coroutines.runBlocking
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi.EC
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ListRepositoriesUseCaseTest {
    private lateinit var useCase: ListRepositoriesUseCase

    @Mock
    private lateinit var repository: ListRepositoriesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = ListRepositoriesUseCase(repository)
    }

    @Test
    fun getListRepositoriesReturnSuccess() {
        val expected = ListRepositoriesContract.ListRepositoriesResult.Success(arrayListOf())

        val networkResponse = NetworkResponse.Success<List<RepositoryEntity>>(arrayListOf())

        runBlocking {

            Mockito.`when`(repository.getRepositories(1)).thenReturn(networkResponse)

            val actual = useCase.execute(1)

            Assert.assertEquals(
                expected,
                actual
            )
        }
    }

    @Test
    fun getListRepositoriesReturnSuccessLocal() {
        val expected = ListRepositoriesContract.ListRepositoriesResult.SuccessLocal(arrayListOf())

        val networkResponse = NetworkResponse.SuccessLocal<List<RepositoryEntity>>(arrayListOf())

        runBlocking {

            Mockito.`when`(repository.getRepositories(1)).thenReturn(networkResponse)

            val actual = useCase.execute(1)

            Assert.assertEquals(
                expected,
                actual
            )
        }
    }

    @Test
    fun getListRepositoriesReturnFailure() {
        val exception = Exception()
        val expected = ListRepositoriesContract.ListRepositoriesResult.Failure(exception)

        val networkResponse = NetworkResponse.Failure<List<RepositoryEntity>>(exception)

        runBlocking {

            Mockito.`when`(repository.getRepositories(1)).thenReturn(networkResponse)

            val actual = useCase.execute(1)

            Assert.assertEquals(
                expected,
                actual
            )
        }
    }
}