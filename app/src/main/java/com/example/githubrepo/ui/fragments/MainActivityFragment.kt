package com.example.githubrepo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepo.GithubApplication
import com.example.githubrepo.R
import com.example.githubrepo.ui.repolist.RepoListAdapter
import com.example.githubrepo.viewmodel.MainViewModel
import com.example.githubrepo.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*


class MainActivityFragment: Fragment() {

    lateinit var mainViewModel: MainViewModel
    private lateinit var repoAdapter: RepoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initialise()
    }

    private fun initRecyclerView(){

        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)

            repoAdapter = RepoListAdapter()
            adapter = repoAdapter
        }
    }

    fun initialise()
    {
        val repository = (activity?.application as GithubApplication).repository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.repos.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it.items.size.toString(), Toast.LENGTH_SHORT).show()
            repoAdapter.updateList(it.items)
        })
    }
}