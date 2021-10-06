package com.example.harrypottercharacters.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Wand")
data class Wand(
    @PrimaryKey
    val id : Int,
    val wood : String = "undefined",
    val core : String = "undefined",
    val length : String = "undefined"
) {
}