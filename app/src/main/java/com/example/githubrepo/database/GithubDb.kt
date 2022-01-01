package com.example.githubrepo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubrepo.models.Repo

@Database(entities = [Repo::class], version = 1)
abstract class GithubDb:RoomDatabase() {

    abstract fun githubDao() : GithubDao

    companion object{
        @Volatile
        private var instance: GithubDb? = null

        fun getDatabase(context: Context): GithubDb {
            if (instance == null) {
                synchronized(this){
                    instance = Room.databaseBuilder(context,
                        GithubDb::class.java,
                        "repos")
                        .build()
                }
            }
            return instance!!
        }
    }
}