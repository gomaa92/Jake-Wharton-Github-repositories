package com.example.jakewhartongithubrepositories.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity

@Database(
    entities = [RepositoryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(VentriDatabaseConverter::class)
abstract class RepositoryDB : RoomDatabase() {
    abstract fun getRepositoryDao(): RepositoryDao
}