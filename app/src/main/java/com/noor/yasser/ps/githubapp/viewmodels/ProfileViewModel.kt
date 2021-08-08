package com.noor.yasser.ps.githubapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.noor.yasser.ps.githubapp.repositories.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val dataRepository: DataRepository,
    application: Application
) : AndroidViewModel(application) {


    fun detailUser(username: String, isExists: (Boolean) -> Unit) {
        viewModelScope.launch {
            dataRepository.detailUser(username,isExists)
        }
    }

    fun getUserDataStateFlow() =
        dataRepository.getUserDataStateFlow()

}