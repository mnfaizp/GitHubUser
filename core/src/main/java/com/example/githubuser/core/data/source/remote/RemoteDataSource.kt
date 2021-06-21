package com.example.githubuser.core.data.source.remote

import android.util.Log
import com.example.githubuser.core.data.source.remote.network.ApiResponse
import com.example.githubuser.core.data.source.remote.network.ApiService
import com.example.githubuser.core.data.source.remote.response.UserDetailResponse
import com.example.githubuser.core.data.source.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getUsers(): Flow<ApiResponse<List<UserResponse>>> {
        return flow {
            try {
                val response = apiService.getUsers()

                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.d("Remote Data Source", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUser(user: String?): Flow<ApiResponse<UserDetailResponse>> {
        return flow {
            try {
                val response = apiService.getUser(user)

                emit(ApiResponse.Success(response))

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.d("Remote Data Source", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}