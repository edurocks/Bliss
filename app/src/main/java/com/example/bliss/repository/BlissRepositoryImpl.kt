package com.example.bliss.repository

import com.example.bliss.database.entity.EmojiEntity

interface BlissRepositoryImpl {
    suspend fun insertEmojiList()
    suspend fun insertEmojis(emojis : List<EmojiEntity>)
    suspend fun getEmojisFromDb() : List<EmojiEntity>

}