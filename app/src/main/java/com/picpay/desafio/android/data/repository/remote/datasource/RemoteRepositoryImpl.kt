package com.picpay.desafio.android.data.repository.remote.datasource

import android.content.Context
import com.picpay.desafio.android.domain.repository.RemoteRepository
import com.picpay.desafio.android.data.repository.remote.service.PicPayService
import com.picpay.desafio.android.data.repository.remote.model.response.RetrofitResponse

class RemoteRepositoryImpl(private val context: Context, private val api: PicPayService) :
    RemoteRepository {

    override suspend fun getAllContacts()
            = RetrofitResponse(context) { api.getAllContacts() }.result()

}