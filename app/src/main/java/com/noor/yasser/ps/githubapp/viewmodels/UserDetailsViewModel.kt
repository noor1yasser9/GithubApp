package com.noor.yasser.ps.githubapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.noor.yasser.ps.githubapp.repositories.DataProfileRepository
import com.noor.yasser.ps.githubapp.repositories.DataUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    val dataProfileRepository: DataUserRepository,
    application: Application
) : AndroidViewModel(application) {


    fun detailUser(username: String, isExists: (Boolean) -> Unit) {
        viewModelScope.launch {
            dataProfileRepository.detailUser(username, isExists)
        }
    }



    fun getUserDataStateFlow() =
        dataProfileRepository.getUserDataStateFlow()

    fun getUserRepoStateFlow() =
        dataProfileRepository.getUserRepoStateFlow()
}