package com.example.githubuser.di

import com.example.githubuser.core.domain.usecase.UserUseCase
import com.example.githubuser.core.domain.usecase.UserUseInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideUserUseCase(userInteractor: UserUseInteractor): UserUseCase
}