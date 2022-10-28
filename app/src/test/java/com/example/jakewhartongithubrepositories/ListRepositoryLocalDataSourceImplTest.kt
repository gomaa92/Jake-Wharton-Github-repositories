package com.example.jakewhartongithubrepositories

import com.example.jakewhartongithubrepositories.core.data.local.RepositoryDao
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.local.ListRepositoryLocalDataSource
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.local.ListRepositoryLocalDataSourceImpl
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ListRepositoryLocalDataSourceImplTest {
    lateinit var localSource: ListRepositoryLocalDataSource

    @Mock
    lateinit var repositoryDao: RepositoryDao

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        localSource = ListRepositoryLocalDataSourceImpl(
            repositoryDao
        )
    }

    @Test
    fun getListRepositoriesFromDB() {

        val expected = arrayListOf<RepositoryEntity>()

        runBlocking {

            Mockito.`when`(repositoryDao.getAllRepositories()).thenReturn(arrayListOf())

            val actual = localSource.getAllRepository()

            Assert.assertEquals(
                expected,
                actual
            )
        }
    }

    @Test
    fun insertRepositoriesToDB() {

        runBlocking {
            Mockito.`when`(
                repositoryDao.insertAll(
                    arrayListOf(
                        RepositoryEntity(),
                        RepositoryEntity()
                    )
                )
            ).thenReturn(Unit)

            val actual = localSource.getAllRepository()

            Assert.assertNull(
                actual
            )
        }
    }
}