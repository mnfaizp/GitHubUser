package com.example.githubuser.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.githubuser.core.domain.model.UserDetail

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "userId")
    @NonNull
    var userId: String,

    @ColumnInfo(name = "login")
    var login: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String,

    @Embedded val userDetail: UserDetail?

)
