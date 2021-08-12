package com.noor.yasser.ps.githubapp.db

import androidx.room.*
import com.noor.yasser.ps.githubapp.model.repo.RepositoryItem

@Dao
interface GithubDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepository(result: RepositoryItem): Long

    @Query("SELECT * FROM RepositoryItem")
    suspend fun getAllRepository(): List<RepositoryItem>

    @Query("DELETE FROM RepositoryItem WHERE id = :id")
    suspend fun deleteRepository(id: Int)

    @Query("SELECT EXISTS (SELECT 1 FROM RepositoryItem WHERE id = :id)")
    fun exists(id: Int): Boolean

}