package com.example.githubrepo.ui.repolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepo.R
import com.example.githubrepo.models.Repo
import com.example.githubrepo.ui.fragments.DetailFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.repo_list_item.view.*

class RepoListAdapter()
    : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    private var repoList: List<Repo> = ArrayList()
    private var callback: AdapterCallback? = null

    fun setListener(listener: AdapterCallback) {
        callback = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    fun updateList(list: List<Repo>){
        repoList = list
        this.notifyDataSetChanged()
    }

    override fun getItemCount() = repoList.size


    class ViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        fun bind(repo: Repo){
            itemView.repo_head.text = repo.name
            itemView.repo_desc.text = repo?.short_description ?: "NA"
            itemView.setOnClickListener(object:View.OnClickListener{
                override fun onClick(v: View?) {
                    val activity = v!!.context as AppCompatActivity
                    val jsonString = Gson().toJson(repo);
                    val bundle = Bundle()
                    bundle.putString("repoString", jsonString)
                    val detailFragment = DetailFragment()
                    detailFragment.arguments = bundle
                    activity.supportFragmentManager.beginTransaction().replace(R.id.main_fragment, detailFragment).addToBackStack(null).commit()
                }
            })
        }
    }

    interface AdapterCallback {
        fun onMethodCallback()
    }

}