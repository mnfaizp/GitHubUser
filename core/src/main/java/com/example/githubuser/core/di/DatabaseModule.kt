package com.example.githubuser.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): com.example.githubuser.core.data.source.local.room.UserDatabase =
        Room.databaseBuilder(
            context,
            com.example.githubuser.core.data.source.local.room.UserDatabase::class.java, "User.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideUserDao(database: com.example.githubuser.core.data.source.local.room.UserDatabase): com.example.githubuser.core.data.source.local.room.UserDao =
        database.userDao()
}