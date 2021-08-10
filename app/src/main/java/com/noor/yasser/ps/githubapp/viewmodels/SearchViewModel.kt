package com.noor.yasser.ps.githubapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.noor.yasser.ps.githubapp.repositories.DataUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val repository: DataUserRepository,
    application: Application
) : AndroidViewModel(application) {


    fun searchUser(username: String) {
        viewModelScope.launch {
            repository.userSearch(username)
        }
    }

    fun getUserSearchStateFlow() = repository.getUserSearchStateFlow()
}