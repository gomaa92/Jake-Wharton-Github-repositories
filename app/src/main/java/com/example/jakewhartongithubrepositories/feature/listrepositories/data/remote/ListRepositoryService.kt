package com.example.jakewhartongithubrepositories.feature.listrepositories.data.remote

import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ListRepositoryService {
    @GET("users/JakeWharton/repos")
    suspend fun getRepositories(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 15
    ): List<RepositoryEntity>
}