package com.example.harrypottercharacters.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.harrypottercharacters.models.Wand
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CharacterConverter {
    @TypeConverter
    fun ActorsListToJson(data : List<String>) : String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun JsonToActorsList(data: String) : List<String>? {

        val type = object : TypeToken<List<String>>(){}.type

        val list : List<String> = Gson().fromJson(data, type)

        return list
    }

}