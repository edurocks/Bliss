package com.example.bliss.model

import com.google.gson.annotations.SerializedName

data class UserReposResponse(@SerializedName("id") val id : Int,
                             @SerializedName("full_name") val name : String)
