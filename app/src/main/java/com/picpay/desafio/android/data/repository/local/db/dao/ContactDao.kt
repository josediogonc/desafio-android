package com.picpay.desafio.android.data.repository.local.db.dao

import androidx.room.*
import com.picpay.desafio.android.domain.model.user.User

@Dao
interface ContactDao {
    @Query("SELECT * FROM Contact")
    suspend fun getAll(): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAll(contacts: List<User>)

    @Delete(entity = User::class)
    suspend fun dropTables(githubProfilesList: List<User>)
}