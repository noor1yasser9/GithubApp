package com.noor.yasser.ps.githubapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.noor.yasser.ps.githubapp.repositories.DataProfileRepository
import com.noor.yasser.ps.githubapp.repositories.DatabaseRepository
import com.noor.yasser.ps.githubapp.utils.ResultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@HiltViewModel
class HomeViewModel @Inject constructor(
    val dataProfileRepository: DataProfileRepository,
    val databaseRepository: DatabaseRepository,
    application: Application
) : AndroidViewModel(application) {

    fun getAllRepo() = viewModelScope.launch {
        databaseRepository.getAllRepos()
    }

    fun getRepoAllLiveData(): StateFlow<ResultResponse<Any>> =
        databaseRepository.getRepoAllLiveData()
    fun deleteRepo(id: Int) {
        viewModelScope.launch {
            databaseRepository.getDeleteRepo(id)
            getAllRepo()
        }
    }

    init {
        getAllRepo()
    }
}