package com.noor.yasser.ps.githubapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.noor.yasser.ps.githubapp.repositories.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    val dataRepository: DataRepository,
    application: Application
) : AndroidViewModel(application) {
}