package com.example.jakewhartongithubrepositories.core.di

import android.content.Context
import androidx.room.Room
import com.example.jakewhartongithubrepositories.core.data.local.RepositoryDB
import com.example.jakewhartongithubrepositories.core.data.local.RepositoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RepositoryDB {
        return Room.databaseBuilder(context, RepositoryDB::class.java, "Ventri_DB")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideRepositoryDao(db: RepositoryDB): RepositoryDao = db.getRepositoryDao()
}