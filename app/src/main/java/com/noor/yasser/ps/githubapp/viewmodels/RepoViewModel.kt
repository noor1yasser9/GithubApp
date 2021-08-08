package com.noor.yasser.ps.githubapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.noor.yasser.ps.githubapp.repositories.DataProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    val dataProfileRepository: DataProfileRepository,
    application: Application
) : AndroidViewModel(application) {
}