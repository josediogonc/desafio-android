package com.picpay.desafio.android.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.model.user.User
import com.picpay.desafio.android.utils.load
import com.picpay.desafio.android.utils.visible

class UserListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) =
        setupItemView(user).also { loadUserImage(user) }

    private fun loadUserImage(user: User) {
        user.img.load(itemView)
    }

    private fun setupItemView(user: User) {
        val binding = ListItemUserBinding.bind(itemView)
        binding.apply {
            name.text = user.name
            username.text = user.username
            progressBar.visible()
        }
    }
}