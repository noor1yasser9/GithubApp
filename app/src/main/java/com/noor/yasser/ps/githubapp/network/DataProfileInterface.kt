package com.noor.yasser.ps.githubapp.network

import com.noor.yasser.ps.githubapp.model.UserModel
import com.noor.yasser.ps.githubapp.model.FollowersItem
import com.noor.yasser.ps.githubapp.model.repo.RepositoryItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DataProfileInterface {

    @GET("/users/{username}")
    suspend fun detailUser(@Path("username") username: String): Response<UserModel>


    @GET("/users/{username}/followers")
    suspend fun userFollowers(@Path("username") username: String): Response<List<FollowersItem>>

    @GET("/users/{username}/following")
    suspend fun userFollowing(@Path("username") username: String): Response<List<FollowersItem>>

    @GET("/users/{username}/repos")
    suspend fun userRepo(@Path("username") username: String): Response<List<RepositoryItem>>

}