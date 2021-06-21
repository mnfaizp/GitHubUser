package com.example.githubuser.core.data.source.local

import com.example.githubuser.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val userDao: com.example.githubuser.core.data.source.local.room.UserDao) {

    fun getAllUser(): Flow<List<UserEntity>> =
        userDao.getAllUser()

    fun getUser(userId: String?): Flow<UserEntity> =
        userDao.getUser(userId.toString())

    fun updateUserDetail(user: UserEntity) = userDao.updateUser(user)

    fun getFavoriteUser() = userDao.getAllFavoriteUsers()

    suspend fun insertUser(userList: List<UserEntity>) =
        userDao.insertUser(userList)

    fun setFavoriteUser(
        user: UserEntity?,
        newState: Boolean
    ) {
        user?.isFavorite = newState
        userDao.updateUser(user)
    }

}