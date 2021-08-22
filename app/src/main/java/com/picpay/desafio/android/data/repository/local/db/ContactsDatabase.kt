package com.picpay.desafio.android.data.repository.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.repository.local.db.dao.ContactDao
import com.picpay.desafio.android.domain.model.user.User

@Database(entities = [User::class], version = 2)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        const val DATABASE_NAME = "contacts-database"
    }
}

