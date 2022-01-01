package com.example.githubrepo.remote

import com.example.githubrepo.models.RepoList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {
    @GET("/search/topics")
    suspend fun getRepos(@Query("q") topic: String) : Response<RepoList>
}