package com.example.githubrepo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubrepo.R
import com.example.githubrepo.models.Repo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(requireArguments().getString("repoString") != null)
        {
            var jsonData = requireArguments().getString("repoString")
            println(jsonData)
            val repodata = Gson().fromJson(jsonData, Repo::class.java)
            println(repodata.description)
            view.repo_desc.text = repodata.description ?: "NA"
            view.repo_head.text = repodata.name ?: "NA"

        }

    }

}