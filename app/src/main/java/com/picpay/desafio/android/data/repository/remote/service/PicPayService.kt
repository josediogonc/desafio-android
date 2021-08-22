package com.picpay.desafio.android.data.repository.remote.service

import com.picpay.desafio.android.domain.model.user.User
import retrofit2.Response
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getAllContacts(): Response<List<User>>
}