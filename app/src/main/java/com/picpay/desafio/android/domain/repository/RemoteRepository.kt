package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.user.User
import com.picpay.desafio.android.data.repository.remote.model.resource.Resource

interface RemoteRepository {

    suspend fun getAllContacts() : Resource<List<User>>

}