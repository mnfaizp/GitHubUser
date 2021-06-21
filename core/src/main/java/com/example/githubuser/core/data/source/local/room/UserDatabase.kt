package com.example.githubuser.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [com.example.githubuser.core.data.source.local.entity.UserEntity::class],
    version = 2,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): com.example.githubuser.core.data.source.local.room.UserDao
}