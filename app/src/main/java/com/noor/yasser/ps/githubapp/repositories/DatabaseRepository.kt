package com.noor.yasser.ps.githubapp.repositories

import com.noor.yasser.ps.githubapp.db.GithubDAO
import com.noor.yasser.ps.githubapp.db.GithubDB
import com.noor.yasser.ps.githubapp.model.repo.RepositoryItem
import com.noor.yasser.ps.githubapp.utils.ResultResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepository @Inject constructor(val githubDB: GithubDAO) {

    private val repoInsertLiveData: MutableStateFlow<ResultResponse<Any>> =
        MutableStateFlow(ResultResponse.empty(""))
    private val repoAllLiveData: MutableStateFlow<ResultResponse<Any>> =
        MutableStateFlow(ResultResponse.empty(""))
    private val repoIsExistsLiveData: MutableStateFlow<ResultResponse<Any>> =
        MutableStateFlow(ResultResponse.empty(""))
    private val repoDeleteLiveData: MutableStateFlow<ResultResponse<Any>> =
        MutableStateFlow(ResultResponse.empty(""))

    fun insertMovie(item: RepositoryItem) {
        CoroutineScope(Dispatchers.IO).launch {
            repoInsertLiveData.emit(ResultResponse.loading("loading"))
            val id = githubDB.insertRepository(item)
            if (id > 0)
                repoInsertLiveData.emit(ResultResponse.success(id))
            else
                repoInsertLiveData.emit(ResultResponse.error("Ooops:", ""))
        }
    }

    fun getAllRepos() {
        CoroutineScope(Dispatchers.IO).launch {
            repoAllLiveData.emit(ResultResponse.loading("loading"))
            val data = githubDB.getAllRepository()
            repoAllLiveData.emit(ResultResponse.success(data))
        }
    }

    fun getIfExists(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repoIsExistsLiveData.emit(ResultResponse.loading("loading"))
            val data = githubDB.exists(id)
            repoIsExistsLiveData.emit(ResultResponse.success(data))
        }
    }

    fun getDeleteRepo(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repoDeleteLiveData.emit(ResultResponse.loading("loading"))
            val data = githubDB.deleteRepository(id)
            repoDeleteLiveData.emit(ResultResponse.success(data))
        }
    }

}