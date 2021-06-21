package com.example.githubuser.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val userId: String,
    val login: String,
    val avatarUrl: String,
    val url: String,
    val isFavortie: Boolean,
    val userDetail: UserDetail?
) : Parcelable