package com.noor.yasser.ps.githubapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.noor.yasser.ps.githubapp.model.repo.RepositoryItem

@TypeConverters(value = [ConverterDB::class])
@Database(entities = [RepositoryItem::class], version = 7, exportSchema = false)
abstract class GithubDB : RoomDatabase() {

    abstract fun gethubDAO(): GithubDAO
}