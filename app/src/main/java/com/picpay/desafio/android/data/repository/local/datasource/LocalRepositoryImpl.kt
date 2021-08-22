package com.picpay.desafio.android.data.repository.local.datasource

import com.picpay.desafio.android.domain.model.user.User
import com.picpay.desafio.android.data.repository.local.db.ContactsDatabase
import com.picpay.desafio.android.domain.repository.LocalRepository

class LocalRepositoryImpl(private val database: ContactsDatabase) : LocalRepository {

    override suspend fun saveAll(contacts: List<User>) = database.contactDao().saveAll(contacts)

    override suspend fun getAll(): List<User> = database.contactDao().getAll()

}
