package com.example.githubuser.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,
)

