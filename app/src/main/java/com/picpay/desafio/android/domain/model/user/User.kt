package com.picpay.desafio.android.domain.model.user

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(primaryKeys = ["id"], tableName = "Contact")
data class User(

    @ColumnInfo(name = "id")
    @field:Json(name = "id") val id: Int,

    @ColumnInfo(name = "img")
    @field:Json(name = "img") val img: String,

    @ColumnInfo(name = "name")
    @field:Json(name = "name") val name: String,

    @ColumnInfo(name = "username")
    @field:Json(name = "username") val username: String

) : Parcelable