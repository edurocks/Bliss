package com.example.bliss.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
class EmojiEntity(@PrimaryKey
                  @ColumnInfo(name = "name") var name : String = "",
                  @ColumnInfo(name = "url") var url : String = "") : Parcelable