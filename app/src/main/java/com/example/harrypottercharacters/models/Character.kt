package com.example.harrypottercharacters.models

data class Character(
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
    var wand : Wand,
    var patronus : String,
    var hogwardsStudent : Boolean,
    var hogwardsStaff : Boolean,
    var actor : String,
    var alternate_actors : List<String>,
    var alive : Boolean,
    var image : String
    ) {

}
