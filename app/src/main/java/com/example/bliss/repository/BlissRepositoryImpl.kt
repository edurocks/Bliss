package com.example.bliss.repository

import androidx.paging.PagingData
import com.example.bliss.database.entity.EmojiEntity
import com.example.bliss.database.entity.UserAvatar
import com.example.bliss.model.UserReposResponse
import kotlinx.coroutines.flow.Flow

interface BlissRepositoryImpl {
    suspend fun insertEmojiList()
    suspend fun getAvatar(name : String)
    suspend fun insertEmojis(emojis : List<EmojiEntity>)
    suspend fun insertAvatar(userAvatar: UserAvatar)
    suspend fun getEmojisFromDb() : List<EmojiEntity>
    suspend fun getAvatarFromDb(name : String) : UserAvatar
    suspend fun getAllAvatars() : List<UserAvatar>
    suspend fun deleteAvatar(userAvatar : UserAvatar)
    fun getUserRepos() : Flow<PagingData<UserReposResponse>>
}