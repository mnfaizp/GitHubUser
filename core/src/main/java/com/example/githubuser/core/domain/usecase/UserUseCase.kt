package com.example.githubuser.core.domain.usecase

import com.example.githubuser.core.data.Resource
import com.example.githubuser.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getAllUser(): Flow<Resource<List<User>>>

    fun getFavoriteUsers(): Flow<List<User>>

    fun getUser(user: User?): Flow<Resource<User>>

    fun setFavoriteUser(user: User, state: Boolean)
}