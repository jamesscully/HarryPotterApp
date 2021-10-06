package com.example.harrypottercharacters.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.harrypottercharacters.data.daos.CharacterDao

@Database(
    entities = [ Character::class ],
    version = 1,
    exportSchema = true
)


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
                        "characters.db"
                    ).allowMainThreadQueries()
                        .build()
                }
                return instance
            }
        }

    }

}