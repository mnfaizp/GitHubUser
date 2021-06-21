package com.example.githubuser.core.domain.usecase

import com.example.githubuser.core.data.Resource
import com.example.githubuser.core.domain.model.User
import com.example.githubuser.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseInteractor @Inject constructor(private val userRepository: IUserRepository) :
    UserUseCase {
    override fun getAllUser(): Flow<Resource<List<User>>> {
        return userRepository.getAllUser()
    }

    override fun getFavoriteUsers(): Flow<List<User>> {
        return userRepository.getFavoriteUsers()
    }

    override fun getUser(user: User?): Flow<Resource<User>> {
        return userRepository.getUser(user)
    }

    override fun setFavoriteUser(user: User, state: Boolean) {
        return userRepository.setFavoriteUser(user, state)
    }

}