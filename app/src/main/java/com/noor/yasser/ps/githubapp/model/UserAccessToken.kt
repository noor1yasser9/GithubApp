package com.noor.yasser.ps.githubapp.model

import com.google.gson.annotations.SerializedName

data class UserAccessToken(
        @SerializedName("id")
        val id: Int,
        @SerializedName("token")
        val token: String,
        @SerializedName("url")
        val url: String
)