package com.picpay.desafio.android.utils

import android.view.View
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun String.load(itemView : View) {

    val binding = ListItemUserBinding.bind(itemView)

    Picasso.get()
        .load(this)
        .error(R.drawable.ic_round_account_circle)
        .into(binding.picture, object : Callback {
            override fun onSuccess() {
                binding.progressBar.gone()
            }

            override fun onError(e: Exception?) {
                binding.progressBar.gone()
            }
        })
}