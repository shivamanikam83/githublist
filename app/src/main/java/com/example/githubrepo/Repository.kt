package com.example.githubrepo

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubrepo.database.GithubDb
import com.example.githubrepo.models.Repo
import com.example.githubrepo.models.RepoList
import com.example.githubrepo.remote.GithubApiService
import com.example.githubrepo.remote.RetrofitClient

class Repository (private val context: Context)
{
    private val apiService: GithubApiService
    private val githubDb: GithubDb
    private val mainLiveData = MutableLiveData<RepoList>()

    init{
        apiService = RetrofitClient.getInstance().create(GithubApiService::class.java)
        githubDb = GithubDb.getDatabase(context)
    }

    val repos: LiveData<RepoList>
        get() = mainLiveData

    suspend fun getRepos(topic: String){

        if(isInternetAvailable(context))
        {
            val result = apiService.getRepos(topic)
            if(result?.body() != null){
                mainLiveData.postValue(result.body())
                githubDb.githubDao().addRepos(result.body()!!.items)
            }
            else
            {
                mainLiveData.postValue(RepoList(0, ArrayList<Repo>()));
            }
        }
        else
        {
            val repos = githubDb.githubDao().getRepos();
            mainLiveData.postValue(RepoList(0, repos))
        }
    }

    suspend fun getReposinBackground(topic: String)
    {
        val result = apiService.getRepos(topic)
        if(result?.body() != null){
            githubDb.githubDao().addRepos(result.body()!!.items)
        }
    }

    fun isInternetAvailable(context: Context): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(
                    NetworkCapabilities.NET_CAPABILITY_INTERNET
                ) ?: false
            } else {
                (@Suppress("DEPRECATION")
                return this.activeNetworkInfo?.isConnected ?: false)
            }
        }
    }
}
