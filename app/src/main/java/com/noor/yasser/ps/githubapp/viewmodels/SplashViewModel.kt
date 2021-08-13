package com.noor.yasser.ps.githubapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    application: Application,
) :
    AndroidViewModel(application) {

    private val mutableLiveData = MutableLiveData<SplashState>()
    val liveData: LiveData<SplashState>
        get() = mutableLiveData

    init {
        GlobalScope.launch {
            delay(3000)
            mutableLiveData.postValue(SplashState.SplashFragment)
        }
    }

    sealed class SplashState {
        object SplashFragment : SplashState()
    }
}
