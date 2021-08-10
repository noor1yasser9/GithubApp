package com.noor.yasser.ps.githubapp.model.search


import com.google.gson.annotations.SerializedName
import com.noor.yasser.ps.githubapp.model.FollowersItem

data class SearchRequest(
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean,
    @SerializedName("items")
    var items: List<FollowersItem>,
    @SerializedName("total_count")
    var totalCount: Int
)