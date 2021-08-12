package com.noor.yasser.ps.githubapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.noor.yasser.ps.githubapp.model.FollowersItem
import com.noor.yasser.ps.githubapp.model.UserModel
import com.noor.yasser.ps.githubapp.model.repo.RepositoryItem

@TypeConverters(value = [ConverterDB::class])
@Database(entities = [RepositoryItem::class, FollowersItem::class, UserModel::class], version = 1)
abstract class GithubDB : RoomDatabase() {

    abstract fun githubDAO(): GithubDAO
}