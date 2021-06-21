package com.example.githubuser.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.githubuser.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(userUseCase: UserUseCase) : ViewModel() {
    val users = userUseCase.getAllUser().asLiveData()
}