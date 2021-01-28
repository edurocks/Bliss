package com.example.bliss.deserialization

import com.example.bliss.model.Emoji
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.json.JSONObject
import java.lang.reflect.Type

class EmojiDeserializer : JsonDeserializer<List<Emoji>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ArrayList<Emoji> {

        val listEmojis = ArrayList<Emoji>()

        val jsonResponse = JSONObject(json.toString())

        val iteratorObj: Iterator<*> = jsonResponse.keys()
        while (iteratorObj.hasNext()){
            val name = iteratorObj.next() as String
            val url = jsonResponse.getString(name)
            val emoji = Emoji(name, url)
            listEmojis.add(emoji)
        }

        return listEmojis
    }
}