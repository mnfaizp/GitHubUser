package com.example.githubuser.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.core.R
import com.example.githubuser.core.databinding.ItemRecyclerUserBinding
import com.example.githubuser.core.domain.model.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    private val listData = ArrayList<User>()
    var onItemClick: ((User) -> Unit)? = null

    fun setData(newListData: List<User>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRecyclerUserBinding.bind(itemView)

        fun bind(data: User) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.avatarUrl)
                    .into(imgViewAvatar)

                tvLoginName.text = data.login
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

}