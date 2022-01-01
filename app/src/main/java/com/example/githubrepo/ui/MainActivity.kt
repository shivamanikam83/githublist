package com.example.githubrepo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubrepo.viewmodel.MainViewModel
import com.example.githubrepo.R
import com.example.githubrepo.ui.repolist.RepoListAdapter

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    private lateinit var repoAdapter: RepoListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}