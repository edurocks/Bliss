package com.example.bliss.network

import com.example.bliss.model.EmojiResponse
import com.example.bliss.model.UserAvatarResponse
import com.example.bliss.model.UserReposResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BlissInterface {

    @GET("emojis")
    suspend fun getEmojiList() : Response<EmojiResponse>

    @GET("users/{name}")
    suspend fun getUserAvatar(@Path("name") name : String) : Response<UserAvatarResponse>

    @GET("users/{name}/repos")
    suspend fun getUserRepos(@Path("name") name : String, @Query("page")  page : Int,
                             @Query("size")  size : Int) : Response<UserReposResponse>
}