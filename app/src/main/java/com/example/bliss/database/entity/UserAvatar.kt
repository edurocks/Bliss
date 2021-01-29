package com.example.bliss.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserAvatar(@PrimaryKey
                      @ColumnInfo(name = "id") var id : Long = 0,
                      @ColumnInfo(name = "avatar_url") var url : String = "",
                      @ColumnInfo(name = "name") var name : String = "")