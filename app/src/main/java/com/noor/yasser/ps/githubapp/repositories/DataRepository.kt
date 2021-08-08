package com.noor.yasser.ps.githubapp.repositories

import android.util.Log
import com.noor.yasser.ps.githubapp.network.DataInterface
import com.noor.yasser.ps.githubapp.utils.ResultResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(val dataInterface: DataInterface) {

    private val userDataMutableStateFlow: MutableStateFlow<ResultResponse<Any>> =
        MutableStateFlow(ResultResponse.loading(""))

    private val userFollowersMutableStateFlow: MutableStateFlow<ResultResponse<Any>> =
        MutableStateFlow(ResultResponse.loading(""))
    private val userFollowingMutableStateFlow: MutableStateFlow<ResultResponse<Any>> =
        MutableStateFlow(ResultResponse.loading(""))


    fun detailUser(username: String, isExists: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = dataInterface.detailUser(username)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.message == null) {
                            userDataMutableStateFlow.emit(ResultResponse.success(it))
                            isExists(true)
                        } else {
                            isExists(false)
                        }
                    }
                } else {
                    userDataMutableStateFlow.emit(
                        ResultResponse.error(
                            "Ooops: ${response.errorBody()}",
                            response
                        )
                    )
                    isExists(false)
                }
            } catch (e: HttpException) {
                e.printStackTrace()
                userDataMutableStateFlow.emit(ResultResponse.error("Ooops: ${e.message()}", e))
                isExists(false)
            } catch (t: Throwable) {
                t.printStackTrace()
                userDataMutableStateFlow.emit(ResultResponse.error("Ooops: ${t.message}", t))
                isExists(false)
            }
        }
    }

    fun userFollowers(username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = dataInterface.userFollowers(username)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.e("ttttttttt", it.toString())
                        userFollowersMutableStateFlow.emit(ResultResponse.success(it))
                    }
                } else {
                    userDataMutableStateFlow.emit(
                        ResultResponse.error(
                            "Ooops: ${response.errorBody()}",
                            response
                        )
                    )
                }
            } catch (e: HttpException) {
                e.printStackTrace()
                userFollowersMutableStateFlow.emit(ResultResponse.error("Ooops: ${e.message()}", e))
            } catch (t: Throwable) {
                t.printStackTrace()
                userFollowersMutableStateFlow.emit(ResultResponse.error("Ooops: ${t.message}", t))
            }
        }
    }

    fun userFollowing(username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = dataInterface.userFollowing(username)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.e("ttttttttt", it.toString())
                        userFollowingMutableStateFlow.emit(ResultResponse.success(it))
                    }
                } else {
                    userFollowingMutableStateFlow.emit(
                        ResultResponse.error(
                            "Ooops: ${response.errorBody()}",
                            response
                        )
                    )
                }
            } catch (e: HttpException) {
                e.printStackTrace()
                userFollowingMutableStateFlow.emit(ResultResponse.error("Ooops: ${e.message()}", e))
            } catch (t: Throwable) {
                t.printStackTrace()
                userFollowingMutableStateFlow.emit(ResultResponse.error("Ooops: ${t.message}", t))
            }
        }
    }

    fun getUserDataStateFlow(): StateFlow<ResultResponse<Any>> = userDataMutableStateFlow
    fun getUserFollowersStateFlow(): StateFlow<ResultResponse<Any>> = userFollowersMutableStateFlow
    fun getUserFollowingStateFlow(): StateFlow<ResultResponse<Any>> = userFollowingMutableStateFlow
}