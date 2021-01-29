package com.example.bliss.model

import com.google.gson.annotations.SerializedName

data class UserAvatarResponse(@SerializedName("id") val id : Long,
                              @SerializedName("avatar_url") val url : String)
