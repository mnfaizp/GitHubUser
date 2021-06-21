package com.example.githubuser.core.data

import android.util.Log
import com.example.githubuser.core.data.source.local.entity.UserEntity
import com.example.githubuser.core.data.source.remote.network.ApiResponse
import com.example.githubuser.core.data.source.remote.response.UserDetailResponse
import com.example.githubuser.core.data.source.remote.response.UserResponse
import com.example.githubuser.core.domain.model.User
import com.example.githubuser.core.domain.repository.IUserRepository
import com.example.githubuser.core.utils.AppExecutors
import com.example.githubuser.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSource: com.example.githubuser.core.data.source.remote.RemoteDataSource,
    private val localDataSource: com.example.githubuser.core.data.source.local.LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository {
    override fun getAllUser(): Flow<Resource<List<User>>> =
        object :
            com.example.githubuser.core.data.NetworkBoundResource<List<User>, List<UserResponse>>() {
            override fun loadFromDB(): Flow<List<User>> {
                return localDataSource.getAllUser().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<User>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getUsers()

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapResponseToEntity(data)
                localDataSource.insertUser(userList)
            }
        }.asFlow()


    override fun getFavoriteUsers(): Flow<List<User>> {
        return localDataSource.getFavoriteUser().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getUser(user: User?): Flow<Resource<User>> =
        object : NetworkBoundResource<User, UserDetailResponse>() {
            override fun loadFromDB(): Flow<User> {
                Log.d("check", user?.userId.toString())
                return localDataSource.getUser(user?.userId).map {
                    DataMapper.mapSingleEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: User?): Boolean =
                data?.userDetail == null

            override suspend fun createCall(): Flow<ApiResponse<UserDetailResponse>> =
                remoteDataSource.getUser(user?.userId)

            override suspend fun saveCallResult(data: UserDetailResponse) {
                val dataDetail = DataMapper.mapUserDetailResponseToDomain(data)
                appExecutors.diskIO().execute {
                    localDataSource.updateUserDetail(
                        UserEntity(
                            userId = user!!.userId,
                            login = user.login,
                            avatarUrl = user.avatarUrl,
                            url = user.url,
                            isFavorite = user.isFavortie,
                            userDetail = dataDetail
                        )
                    )
                }

            }

        }.asFlow()


    override fun setFavoriteUser(user: User, state: Boolean) {
        val userEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute { localDataSource.setFavoriteUser(userEntity, state) }
    }


}