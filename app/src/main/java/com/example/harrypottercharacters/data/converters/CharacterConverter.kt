package com.example.harrypottercharacters.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CharacterConverter {

    // for simplicity, we just convert to / from json (could use CSV however)

    @TypeConverter
    fun ActorsListToJson(data : List<String>) : String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun JsonToActorsList(data: String) : List<String>? {
        val type = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(data, type)
    }

}