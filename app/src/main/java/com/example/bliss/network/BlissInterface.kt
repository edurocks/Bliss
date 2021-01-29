package com.example.bliss.network

import com.example.bliss.model.EmojiResponse
import com.example.bliss.model.UserAvatarResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BlissInterface {

    @GET("emojis")
    suspend fun getEmojiList() : Response<EmojiResponse>

    @GET("users/{name}")
    suspend fun getUserAvatar(@Path("name") name : String) : Response<UserAvatarResponse>
}