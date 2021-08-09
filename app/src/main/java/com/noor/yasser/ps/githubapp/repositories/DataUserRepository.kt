package com.noor.yasser.ps.githubapp.repositories

import com.noor.yasser.ps.githubapp.network.DataProfileInterface
import com.noor.yasser.ps.githubapp.network.DataUserInterface
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
class DataUserRepository @Inject constructor(val dataInterface: DataUserInterface) {

    private val userDataMutableStateFlow: MutableStateFlow<ResultResponse<Any>> =
        MutableStateFlow(ResultResponse.loading(""))
    private val userRepoMutableStateFlow: MutableStateFlow<ResultResponse<Any>> =
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
                            userRepo(username)
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
            }
        }
    }

    fun userRepo(username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = dataInterface.userRepo(username)
                if (response.isSuccessful) {
                    response.body()?.let {
                        userRepoMutableStateFlow.emit(ResultResponse.success(it))
                    }
                } else {
                    userRepoMutableStateFlow.emit(
                        ResultResponse.error(
                            "Ooops: ${response.errorBody()}",
                            response
                        )
                    )
                }
            } catch (e: HttpException) {
                e.printStackTrace()
                userRepoMutableStateFlow.emit(ResultResponse.error("Ooops: ${e.message()}", e))
            } catch (t: Throwable) {
                t.printStackTrace()
                userRepoMutableStateFlow.emit(ResultResponse.error("Ooops: ${t.message}", t))
            }
        }
    }

    fun getUserDataStateFlow(): StateFlow<ResultResponse<Any>> = userDataMutableStateFlow
    fun getUserRepoStateFlow(): StateFlow<ResultResponse<Any>> = userRepoMutableStateFlow
}