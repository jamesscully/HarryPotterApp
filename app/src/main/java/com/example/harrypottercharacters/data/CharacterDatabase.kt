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
import kotlinx.coroutines.coroutineScope

@Database(
    entities = [ Character::class, Wand::class ],
    version = 1,
)
@TypeConverters(CharacterConverter::class, WandConverter::class)
abstract class CharacterDatabase : RoomDatabase() {

    abstract val DAO : CharacterDao

    companion object {
        val TAG = "CharacterDatabase"

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
                        .build()
                }
                return instance
            }
        }

        // should only be called when we want to populate
        suspend fun populateDatabase(context: Context, repository: CharacterRepository) {
            coroutineScope {
                val characters = repository.getAllCharacters()
                val db = CharacterDatabase.getInstance(context)
                characters.forEach { character ->
                    db.DAO.insert(character)
                }
            }
        }

    }

}