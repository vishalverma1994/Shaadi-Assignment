package com.assingment.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.assingment.R
import com.assingment.data.local.entities.UserEntity
import com.assingment.databinding.AdapterUserListBinding
import com.assingment.listeners.CallBackHandler
import com.assingment.utils.ACCEPT
import com.assingment.utils.DECLINE
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UserListAdapter(private val onAcceptDeclineCallBack: (Int, String) -> Unit) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var usersList = emptyList<UserEntity>()

    fun getUserList(userList: List<UserEntity>) {
        this.usersList = userList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.adapter_user_list,
                parent,
                false
            ), parent.context
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(usersList[position])
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    inner class ViewHolder(private val binding: AdapterUserListBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root), CallBackHandler {

        override fun onDeclineButtonCallBack() {
            onAcceptDeclineCallBack(absoluteAdapterPosition, DECLINE)
        }

        override fun onAcceptButtonCallBack() {
            onAcceptDeclineCallBack(absoluteAdapterPosition, ACCEPT)
        }

        fun bindViews(userEntity: UserEntity) {
            binding.handler = this
            binding.userEntity = userEntity

            Glide.with(context).load(userEntity.picture?.medium).circleCrop().apply(RequestOptions().placeholder(R.drawable.ic_user_placeholder).error(R.drawable.ic_user_placeholder))
                .into(binding.ivUserProfile)
        }
    }
}