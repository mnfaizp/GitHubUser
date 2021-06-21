package com.example.githubuser.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.githubuser.core.domain.model.User
import com.example.githubuser.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailUserViewModel @Inject constructor(private val userUseCase: UserUseCase) :
    ViewModel() {

    fun getUser(user: User) = userUseCase.getUser(user).asLiveData()

    fun setFavoriteUser(user: User, newStatus: Boolean) =
        userUseCase.setFavoriteUser(user, newStatus)

}