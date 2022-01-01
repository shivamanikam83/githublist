package com.example.githubrepo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.githubrepo.models.Repo

@Dao
interface GithubDao {

    @Insert
    suspend fun addRepos(repos: List<Repo>)

    @Update
    suspend fun updateRepos(repos: Repo)

    @Query("SELECT * FROM repos")
    suspend fun getRepos() : List<Repo>
}