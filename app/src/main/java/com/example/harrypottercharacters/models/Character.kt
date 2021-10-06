package com.example.harrypottercharacters.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.harrypottercharacters.data.converters.CharacterConverter

@Entity(tableName = "Character")
data class Character(

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    var name: String,
    // JSON has either List or String; ignore for now, handle with deserializer
    @Transient var alternate_names : MutableList<String>,
    var species : String,
    var gender : String,
    var house : String,
    var dateOfBirth : String,
    @Transient
    var yearOfBirth : Int,
    var wizard : Boolean,
    var ancestry : String,
    var eyeColour : String,
    var hairColour : String,

    @Embedded
    var wand : Wand,
    var patronus : String,
    var hogwardsStudent : Boolean,
    var hogwardsStaff : Boolean,
    var actor : String,

    var alternate_actors : List<String>,
    var alive : Boolean,
    var image : String
    ) {

    constructor() : this(0, "undefined", mutableListOf(), "undefined", "undefined", "undefined", "undefined", 0, false, "undefined", "undefined", "undefined", Wand(0, "undefined", "undefined","undefined"), "undefined", false, false, "undefined", emptyList(), false, "undefined") {

    }

}
