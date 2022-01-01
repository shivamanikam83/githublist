package com.example.githubrepo.worker

import android.app.Application
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.githubrepo.GithubApplication
import com.example.githubrepo.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepoSyncWorker(val context:Context, userParameters: WorkerParameters) :
Worker(context, userParameters) {

    override fun doWork(): Result {
        val repository:Repository = (context as GithubApplication).repository

        try {
            CoroutineScope(Dispatchers.IO).launch {
                repository.getReposinBackground("android")
            }
            return Result.success();
        }catch (error: Throwable) {
            return Result.failure()
        }
    }

}