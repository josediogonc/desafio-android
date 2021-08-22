package com.picpay.desafio.android.data.repository.local.db

import android.content.Context
import androidx.room.Room

object DatabaseFactory {

    fun build(context: Context) =
        Room.databaseBuilder(
            context,
            ContactsDatabase::class.java,
            ContactsDatabase.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

}