package com.example.githubuser.di

import com.example.githubuser.core.domain.usecase.UserUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun userUseCase(): UserUseCase

}