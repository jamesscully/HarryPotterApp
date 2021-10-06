package com.example.harrypottercharacters.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.harrypottercharacters.models.Wand
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WandConverter {
    @TypeConverter
    fun WandToJson(wand: Wand) : String {
        return Gson().toJson(wand)
    }

    @TypeConverter
    fun JsonToWand(json : String) : Wand {
        val type = object : TypeToken<Wand>(){}.type

        return Gson().fromJson(json, type)
    }
}