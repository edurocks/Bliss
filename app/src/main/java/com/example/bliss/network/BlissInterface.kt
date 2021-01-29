package com.example.bliss.network

import com.example.bliss.model.EmojiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface BlissInterface {

    @GET("emojis")
    suspend fun getEmojiList() : Response<EmojiResponse>
}