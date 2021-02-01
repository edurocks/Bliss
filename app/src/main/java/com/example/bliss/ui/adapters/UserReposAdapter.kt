package com.example.bliss.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bliss.databinding.UserReposRowBinding
import com.example.bliss.model.UserReposResponse

class UserReposAdapter : PagingDataAdapter<UserReposResponse, UserReposAdapter.UserReposHolder>(diffCallback) {

    class UserReposHolder(private val itemBinding : UserReposRowBinding)
        : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(userRepos: UserReposResponse) {
            itemBinding.userRepoName.text = userRepos.name
        }
    }

    override fun onBindViewHolder(holder: UserReposHolder, position: Int) {
        getItem(position).let {
            holder.bind(it!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserReposHolder {
        val itemBinding = UserReposRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserReposHolder(itemBinding)
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<UserReposResponse>() {
            override fun areItemsTheSame(oldItem: UserReposResponse, newItem: UserReposResponse): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserReposResponse, newItem: UserReposResponse): Boolean =
                    oldItem == newItem
        }
    }
}