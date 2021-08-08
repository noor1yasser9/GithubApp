package com.noor.yasser.ps.githubapp.network

import com.noor.yasser.ps.githubapp.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DataInterface {

    @GET("/users/{username}")
    suspend fun detailUser(@Path("username") username: String): Response<UserModel>

}