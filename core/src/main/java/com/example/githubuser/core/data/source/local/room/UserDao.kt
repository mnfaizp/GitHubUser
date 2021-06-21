package com.example.githubuser.core.data.source.local.room

import androidx.room.*
import com.example.githubuser.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUser(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE isFavorite = 1")
    fun getAllFavoriteUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userList: List<UserEntity>)

    @Update
    fun updateUser(user: UserEntity?)

    @Query("SELECT * FROM users WHERE userId = :userId")
    fun getUser(userId: String): Flow<UserEntity>

}