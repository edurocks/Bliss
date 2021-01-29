package com.example.bliss.database.dao

import androidx.room.*
import com.example.bliss.database.entity.EmojiEntity
import com.example.bliss.database.entity.UserAvatar

@Dao
interface BlissDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmojis(emojis : List<EmojiEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAvatar(userAvatar : UserAvatar)

    @Delete
    suspend fun deleteAvatar(userAvatar : UserAvatar)

    @Query("SELECT * FROM emojientity")
    suspend fun getEmojis(): List<EmojiEntity>

    @Query("SELECT * FROM useravatar")
    suspend fun getAllAvatars(): List<UserAvatar>

    @Query("SELECT * FROM useravatar WHERE name = :name")
    suspend fun getAvatar(name : String) : UserAvatar

}