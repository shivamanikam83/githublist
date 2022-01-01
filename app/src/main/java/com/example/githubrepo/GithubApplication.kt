package com.example.githubrepo

import android.app.Application
import androidx.work.*
import com.example.githubrepo.worker.RepoSyncWorker
import java.util.concurrent.TimeUnit

class GithubApplication:Application() {
    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        repository = Repository(applicationContext)
        val workContraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequest.Builder(RepoSyncWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(workContraint)
            .build()

        WorkManager.getInstance().enqueue(workRequest);
    }
}