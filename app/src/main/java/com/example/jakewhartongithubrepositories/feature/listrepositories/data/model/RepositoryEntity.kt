package com.example.jakewhartongithubrepositories.feature.listrepositories.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "repository-entity")
data class RepositoryEntity(
    @PrimaryKey
    @SerializedName("id") var id: Int = 0,
    @SerializedName("node_id") var nodeId: String? = "",
    @SerializedName("name") var name: String? = "",
    @SerializedName("full_name") var fullName: String? = "",
    @SerializedName("owner") var owner: RepositoryOwner? =RepositoryOwner(),
    @SerializedName("html_url") var htmlUrl: String? = "",
    @SerializedName("description") var description: String? = "",
    @SerializedName("url") var url: String? = ""
) : Parcelable

@Entity
@Parcelize
data class RepositoryOwner(
    @SerializedName("id") var id: Int? = 0,
    @SerializedName("node_id") var nodeId: String? = "",
    @SerializedName("avatar_url") var avatarUrl: String? = "",
    @SerializedName("gravatar_id") var gravatarId: String? = "",
    @SerializedName("url") var url: String? = "",
    @SerializedName("type") var type: String? = "",
) : Parcelable
