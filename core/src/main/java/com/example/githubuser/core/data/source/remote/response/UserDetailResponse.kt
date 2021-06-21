package com.example.githubuser.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(

    @field:SerializedName("twitter_username")
    val twitterUsername: String? = null,

    @field:SerializedName("bio")
    val bio: String? = null,

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("company")
    val company: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("public_repos")
    val publicRepos: Int? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("organizations_url")
    val organizationsUrl: String? = null,


    @field:SerializedName("public_gists")
    val publicGists: Int? = null,

    @field:SerializedName("followers")
    val followers: Int? = null,


    @field:SerializedName("following")
    val following: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("location")
    val location: String? = null,

    )
