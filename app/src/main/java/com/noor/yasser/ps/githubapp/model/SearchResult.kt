package com.noor.yasser.ps.githubapp.model

import com.google.gson.annotations.SerializedName
import com.noor.yasser.ps.githubapp.model.Repo

data class SearchResult(
    @SerializedName("incomplete_results")
        val incompleteResults: Boolean,
    @SerializedName("items")
        val items: List<Repo>,
    @SerializedName("total_count")
        val totalCount: Int
)
