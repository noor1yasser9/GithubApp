package com.noor.yasser.ps.githubapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.noor.yasser.ps.githubapp.model.repo.Owner

class ConverterDB {

    @TypeConverter
    fun ownerToString(owner: Owner): String {
        return Gson().toJson(owner)
    }

    @TypeConverter
    fun stringToOwner(owner: String): Owner {
        return Gson().fromJson(owner, Owner::class.java)
    }

}