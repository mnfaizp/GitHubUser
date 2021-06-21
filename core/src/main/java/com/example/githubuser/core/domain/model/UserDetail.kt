package com.example.githubuser.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetail(
    val name: String?,
    val follower: Int?,
    val following: Int?,
    val repository: Int?,
    val email: String?,
    val location: String?
) : Parcelable