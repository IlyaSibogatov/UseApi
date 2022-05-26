package com.example.useapi.screens.UsersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.useapi.databinding.UserItemBinding
import com.example.useapi.model.ReqresUser
import kotlinx.android.synthetic.main.user_item.view.*

class UsersListRecyclerAdapter(
    private val layoutInflater: LayoutInflater,
    private val clickListener: UserItemClickListener
) : ListAdapter<ReqresUser, UsersListRecyclerAdapter.MyViewHolder>(DIFF_CALLBACK) {

    fun setList(list: List<ReqresUser>) {
        submitList(list.toMutableList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.binding.apply {
            userCard.tv_first_name.text = user.first_name
            userCard.tv_last_name.text = user.last_name
            userCard.tv_address.text = user.email
        }
        holder.binding.userCard.setOnClickListener {
            clickListener.onUserClicked(user)
        }
    }

    inner class MyViewHolder(val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (layoutPosition != RecyclerView.NO_POSITION) {
                    val user = getItem(layoutPosition)
                    clickListener.onUserClicked(user)
                }
            }
        }
    }

    interface UserItemClickListener {
        fun onUserClicked(user: ReqresUser)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ReqresUser> =
            object : DiffUtil.ItemCallback<ReqresUser>() {
                override fun areItemsTheSame(oldItem: ReqresUser, newItem: ReqresUser): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: ReqresUser, newItem: ReqresUser): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
