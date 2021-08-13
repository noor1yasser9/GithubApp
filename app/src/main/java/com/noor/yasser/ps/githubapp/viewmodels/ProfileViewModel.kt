package com.noor.yasser.ps.githubapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.noor.yasser.ps.githubapp.model.repo.RepositoryItem
import com.noor.yasser.ps.githubapp.repositories.DataProfileRepository
import com.noor.yasser.ps.githubapp.repositories.DatabaseRepository
import com.noor.yasser.ps.githubapp.utils.ResultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val dataProfileRepository: DataProfileRepository,
    val databaseRepository: DatabaseRepository,
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

    fun insertRepo(item: RepositoryItem) {
        viewModelScope.launch {
            databaseRepository.insertRepo(item)
        }
    }

    fun getIfExists(id: Int) {
        viewModelScope.launch {
            databaseRepository.getIfExists(id)
        }
    }


    fun getRepoInsertLiveData(): StateFlow<ResultResponse<Any>> =
        databaseRepository.getRepoInsertLiveData()



    fun getRepoIsExistsLiveData(): StateFlow<ResultResponse<Any>> =
        databaseRepository.getRepoIsExistsLiveData()

    fun getRepoDeleteLiveData(): StateFlow<ResultResponse<Any>> =
        databaseRepository.getRepoDeleteLiveData()

    fun getUserDataStateFlow() =
        dataProfileRepository.getUserDataStateFlow()

    fun getUserFollowersStateFlow() =
        dataProfileRepository.getUserFollowersStateFlow()

    fun getUserFollowingStateFlow() =
        dataProfileRepository.getUserFollowingStateFlow()

    fun getUserRepoStateFlow() =
        dataProfileRepository.getUserRepoStateFlow()
}