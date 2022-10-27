package com.example.jakewhartongithubrepositories.core.data.local

import androidx.room.TypeConverter
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryOwner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class VentriDatabaseConverter {
    @TypeConverter
    fun stringToMeasurements(json: String?): List<RepositoryEntity>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<RepositoryEntity?>?>() {}.type
        return gson.fromJson<List<RepositoryEntity>>(json, type)
    }

    @TypeConverter
    fun measurementsToString(list: List<RepositoryEntity?>?): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<RepositoryEntity?>?>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun jsonToOwner(value: String?): RepositoryOwner? = value?.let {
        Gson().fromJson(it, RepositoryOwner::class.java)
    }

    @TypeConverter
    fun ownerToJson(value: RepositoryOwner?): String? = value?.let {
        Gson().toJson(it)
    }
}