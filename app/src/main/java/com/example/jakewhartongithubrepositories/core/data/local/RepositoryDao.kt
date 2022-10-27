package com.example.jakewhartongithubrepositories.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repository: List<RepositoryEntity>)

    @Query("SELECT * FROM `repository-entity`")
    suspend fun getAllRepositories(): List<RepositoryEntity>
}