package com.noor.yasser.ps.githubapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.noor.yasser.ps.githubapp.repositories.DataProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val dataProfileRepository: DataProfileRepository,
    application: Application
) : AndroidViewModel(application) {


    fun detailUser(username: String, isExists: (Boolean) -> Unit) {
        viewModelScope.launch {
            dataProfileRepository.detailUser(username, isExists)
        }
    }

    fun userFollowers(username: String) {
        viewModelScope.launch {
            dataProfileRepository.userFollowers(username)
        }
    }

    fun userFollowing(username: String) {
        viewModelScope.launch {
            dataProfileRepository.userFollowing(username)
        }
    }

    fun getUserDataStateFlow() =
        dataProfileRepository.getUserDataStateFlow()

    fun getUserFollowersStateFlow() =
        dataProfileRepository.getUserFollowersStateFlow()

    fun getUserFollowingStateFlow() =
        dataProfileRepository.getUserFollowingStateFlow()

    fun getUserRepoStateFlow() =
        dataProfileRepository.getUserRepoStateFlow()
}