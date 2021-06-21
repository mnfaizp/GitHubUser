package com.example.githubuser.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.githubuser.core.domain.usecase.UserUseCase

class FavoriteViewModel(userUseCase: UserUseCase) : ViewModel() {

    val favoriteUsers = userUseCase.getFavoriteUsers().asLiveData()

}