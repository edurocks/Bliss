package com.example.bliss.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bliss.database.entity.UserAvatar
import com.example.bliss.databinding.UserAvatarRowBinding
import com.squareup.picasso.Picasso

class UserAvatarAdapter (private val userAvatarList: ArrayList<UserAvatar>,
                         private val userAvatarClickListener: userAvatarRowClickListener)
    : RecyclerView.Adapter<UserAvatarAdapter.UserAvatarHolder>() {

    class UserAvatarHolder(private val itemBinding: UserAvatarRowBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(userAvatar: UserAvatar) {
            Picasso.get().load(userAvatar.url)
                    .resize(200,200)
                    .into(itemBinding.imageUserAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAvatarHolder {
        val itemBinding = UserAvatarRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserAvatarHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserAvatarHolder, position: Int) {
        val userAvatar = userAvatarList[position]
        holder.bind(userAvatar)

        holder.itemView.setOnClickListener {
            userAvatarClickListener.onUserAvatarClickListener(holder.bindingAdapterPosition, userAvatar)
        }
    }

    fun removeAt(position: Int) {
        userAvatarList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, userAvatarList.size)
    }

    override fun getItemCount(): Int {
        return userAvatarList.size
    }

    interface userAvatarRowClickListener {
        fun onUserAvatarClickListener(position: Int, userAvatar: UserAvatar)
    }
}