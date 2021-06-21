package com.example.githubuser.core.utils

import com.example.githubuser.core.data.source.local.entity.UserEntity
import com.example.githubuser.core.data.source.remote.response.UserDetailResponse
import com.example.githubuser.core.data.source.remote.response.UserResponse
import com.example.githubuser.core.domain.model.User
import com.example.githubuser.core.domain.model.UserDetail

object DataMapper {

    fun mapResponseToEntity(input: List<UserResponse>): List<com.example.githubuser.core.data.source.local.entity.UserEntity> {
        val userList = ArrayList<com.example.githubuser.core.data.source.local.entity.UserEntity>()

        input.map {
            val user = com.example.githubuser.core.data.source.local.entity.UserEntity(
                userId = it.login,
                login = it.login,
                isFavorite = false,
                url = it.url,
                avatarUrl = it.avatarUrl,
                userDetail = null
            )
            userList.add(user)
        }
        return userList
    }

    fun mapEntitiesToDomain(input: List<com.example.githubuser.core.data.source.local.entity.UserEntity>): List<User> =
        input.map {
            User(
                userId = it.userId,
                login = it.login,
                url = it.url,
                avatarUrl = it.avatarUrl,
                isFavortie = it.isFavorite,
                userDetail = it.userDetail
            )
        }


    fun mapDomainToEntity(input: User) =
        input.userDetail?.let {
            com.example.githubuser.core.data.source.local.entity.UserEntity(
                userId = input.userId,
                login = input.login,
                url = input.url,
                avatarUrl = input.avatarUrl,
                isFavorite = input.isFavortie,
                userDetail = it
            )
        }

    fun mapUserDetailResponseToDomain(input: UserDetailResponse): UserDetail =
        UserDetail(
            name = input.name,
            follower = input.followers,
            following = input.following,
            repository = input.publicRepos,
            email = input.email,
            location = input.location
        )

    fun mapSingleEntityToDomain(input: UserEntity): User =
        User(
            userId = input.userId,
            login = input.login,
            url = input.url,
            avatarUrl = input.avatarUrl,
            isFavortie = input.isFavorite,
            userDetail = input.userDetail
        )
}