package com.picpay.desafio.android.mock

import com.picpay.desafio.android.domain.model.user.User

object UserMock {

    fun getContacsList() = listOf(
        User(1, "img1", "Paulo Almeida", "Paulo Almeida"),
        User(2, "img2", "Ricardo Melão", "Ricardo Melão"),
        User(3, "img3", "Roberta Alcântara", "Roberta Alcântara"),
        User(4, "img4", "Juliana de Assis", "Juliana de Assis")
    )
}