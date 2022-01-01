package com.example.githubrepo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepo.Repository
import com.example.githubrepo.database.GithubDb
import com.example.githubrepo.models.RepoList
import com.example.githubrepo.remote.GithubApiService
import com.example.githubrepo.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getRepos("android")
        }
    }
    val repos : LiveData<RepoList>
    get() = repository.repos
}