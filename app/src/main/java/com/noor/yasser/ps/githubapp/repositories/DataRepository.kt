package com.noor.yasser.ps.githubapp.repositories

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

    fun getUserDataStateFlow(): StateFlow<ResultResponse<Any>> = userDataMutableStateFlow
}