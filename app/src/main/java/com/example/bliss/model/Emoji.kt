package com.example.bliss.model

import com.google.gson.annotations.SerializedName

data class Emoji (@SerializedName( "name")var name : String = "",
                  @SerializedName( "url") var url : String = "")
