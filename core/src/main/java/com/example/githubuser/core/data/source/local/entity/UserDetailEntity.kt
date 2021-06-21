package com.example.githubuser.core.data.source.local.entity

import androidx.room.ColumnInfo


data class UserDetailEntity(

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "follower")
    var follower: Int?,

    @ColumnInfo(name = "following")
    var following: Int?,

    @ColumnInfo(name = "repository")
    var repository: Int?,

    @ColumnInfo(name = "email")
    var email: String?,

    @ColumnInfo(name = "location")
    var location: String?

)
