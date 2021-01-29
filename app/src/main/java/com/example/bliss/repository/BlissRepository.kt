package com.example.bliss.repository

import com.example.bliss.database.dao.BlissDao
import com.example.bliss.database.entity.EmojiEntity
import com.example.bliss.database.entity.UserAvatar
import com.example.bliss.model.Emoji
import com.example.bliss.network.BlissInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

class BlissRepository @Inject constructor(private val blissInterface: BlissInterface,
                                          private val blissDao: BlissDao)
    : BlissRepositoryImpl {

    override suspend fun insertEmojiList() {

        val response = blissInterface.getEmojiList()

        if (response.isSuccessful && response.code() == HttpsURLConnection.HTTP_OK){
            val result = response.body() as ArrayList<Emoji>

            withContext(Dispatchers.IO){
                result.map {
                    val list = ArrayList<EmojiEntity>()
                    val emoji = EmojiEntity()
                    emoji.name = it.name
                    emoji.url = it.url
                    list.add(emoji)
                    insertEmojis(list)
                }
            }
        }
    }


    override suspend fun getAvatar(name: String) {
        val response = blissInterface.getUserAvatar(name)

        if (response.isSuccessful && response.code() == HttpsURLConnection.HTTP_OK){
            withContext(Dispatchers.IO){
                insertAvatar(UserAvatar(response.body()!!.id, response.body()!!.url, name))
            }
        }
     }

    override suspend fun insertEmojis(emojis: List<EmojiEntity>) =
        blissDao.insertEmojis(emojis)

    override suspend fun insertAvatar(userAvatar: UserAvatar) =
        blissDao.insertAvatar(userAvatar)

    override suspend fun getEmojisFromDb(): List<EmojiEntity> {
        val emojiData = blissDao.getEmojis()
        if (emojiData.isEmpty()) {
            insertEmojiList()
        }
        return emojiData
    }

    override suspend fun getAvatarFromDb(name: String): UserAvatar {

        val userAvatar = blissDao.getAvatar(name)

        if (userAvatar == null){
           getAvatar(name)
        }else {
            return userAvatar
        }

        return userAvatar
    }
}