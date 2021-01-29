package com.example.bliss.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bliss.database.entity.EmojiEntity
import com.example.bliss.database.entity.UserAvatar

@Dao
interface BlissDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmojis(emojis : List<EmojiEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAvatar(userAvatar : UserAvatar)

    @Query("SELECT * FROM emojientity")
    suspend fun getEmojis(): List<EmojiEntity>

    @Query("SELECT * FROM useravatar WHERE name = :name")
    suspend fun getAvatar(name : String) : UserAvatar

}