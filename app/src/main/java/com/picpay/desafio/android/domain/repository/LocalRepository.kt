package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.user.User

interface LocalRepository {

    suspend fun saveAll(contacts: List<User>)

    suspend fun getAll(): List<User>

}
