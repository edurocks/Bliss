package com.example.bliss.model

import com.google.gson.annotations.SerializedName

data class EmojiResponse(@SerializedName("emoji") val list : List<Emoji>)
