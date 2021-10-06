package com.example.harrypottercharacters.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.harrypottercharacters.data.converters.CharacterConverter
import com.example.harrypottercharacters.data.converters.WandConverter
import com.example.harrypottercharacters.data.daos.CharacterDao
import com.example.harrypottercharacters.models.Character
import com.example.harrypottercharacters.models.Wand

@Database(
    entities = [ Character::class, Wand::class ],
    version = 1,
)
@TypeConverters(CharacterConverter::class, WandConverter::class)
abstract class CharacterDatabase : RoomDatabase() {

    abstract val DAO : CharacterDao

    companion object {
        private var _instance : CharacterDatabase? = null


        fun getInstance(context: Context) : CharacterDatabase {
            synchronized(this) {
                var instance : CharacterDatabase? = _instance
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CharacterDatabase::class.java,
                        "characters"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
                return instance
            }
        }

    }

}