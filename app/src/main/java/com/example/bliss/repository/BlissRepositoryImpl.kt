package com.example.bliss.repository

import com.example.bliss.database.entity.EmojiEntity
import com.example.bliss.database.entity.UserAvatar

interface BlissRepositoryImpl {
    suspend fun insertEmojiList()
    suspend fun getAvatar(name : String)
    suspend fun insertEmojis(emojis : List<EmojiEntity>)
    suspend fun insertAvatar(userAvatar: UserAvatar)
    suspend fun getEmojisFromDb() : List<EmojiEntity>
    suspend fun getAvatarFromDb(name : String) : UserAvatar
    suspend fun getAllAvatars() : List<UserAvatar>
    suspend fun deleteAvatar(userAvatar : UserAvatar)
}