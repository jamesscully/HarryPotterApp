package com.example.harrypottercharacters.json

import android.util.Log
import com.example.harrypottercharacters.models.Character
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.ClassCastException
import java.lang.NumberFormatException
import java.lang.reflect.Type

class CharacterDeserializer : JsonDeserializer<Character> {

    val TAG = "WizardDeserializer"

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Character {
        val character = Gson().fromJson(json?.asJsonObject, Character::class.java)
        character.alternate_names = mutableListOf()

        // yearOfBirth can be String or Int, so we will handle it here
        if(json?.asJsonObject?.get("yearOfBirth") != null) {
            val yearOfBirth = json.asJsonObject.get("yearOfBirth")

            if(!yearOfBirth.asString.isNullOrEmpty()) {
                character.yearOfBirth = yearOfBirth.asInt
            }
        }

        try {
            Log.d(TAG, "Deserializing ${character.name}")

            if (json?.asJsonObject?.get("alternate_names") != null) {
                val names = json.asJsonObject.get("alternate_names")

                // JSON provided either has an array of strings, or a singular string
                // This prevents JsonSyntaxException from being raised in the case of a single string
                if (!names.isJsonArray) {
                    character.alternate_names.add(names.asString)
                } else {
                    // parse if it is actually an array
                    character.alternate_names = Gson().fromJson(names, object : TypeToken<List<String>>(){}.type)
                }
            }


        } catch (e: IllegalArgumentException) {
            Log.e(TAG, "Error parsing json")
            e.printStackTrace()
        }

        return character
    }
}