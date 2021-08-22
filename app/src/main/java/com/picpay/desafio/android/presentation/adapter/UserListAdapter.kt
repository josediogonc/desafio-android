package com.picpay.desafio.android.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.user.User
import com.picpay.desafio.android.utils.inflate

class UserListAdapter(private val users: List<User>) : RecyclerView.Adapter<UserListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserListItemViewHolder(parent.inflate(
        R.layout.list_item_user
    ))

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) = holder.bind(users[position])

    override fun getItemCount(): Int = users.size
}