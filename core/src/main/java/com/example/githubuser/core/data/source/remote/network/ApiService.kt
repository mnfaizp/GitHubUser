package com.example.githubuser.core.data.source.remote.network

import com.example.githubuser.core.data.source.remote.response.UserDetailResponse
import com.example.githubuser.core.data.source.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>

    @GET("users/{user}")
    suspend fun getUser(@Path("user") user: String?): UserDetailResponse
}