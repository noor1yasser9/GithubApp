package com.noor.yasser.ps.githubapp.di

import android.app.Application
import androidx.room.Room
import com.noor.yasser.ps.githubapp.db.GithubDAO
import com.noor.yasser.ps.githubapp.db.GithubDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDB(application: Application): GithubDB {
        return Room
            .databaseBuilder(application, GithubDB::class.java, "github4.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(githubDB: GithubDB): GithubDAO {
        return githubDB.githubDAO()
    }

}