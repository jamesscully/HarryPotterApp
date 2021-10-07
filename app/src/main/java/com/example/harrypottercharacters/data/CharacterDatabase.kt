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

        fun populateDatabase(context: Context) {

            // todo convert this to more elegant method

//            api.getAllCharacters().enqueue(object: retrofit2.Callback<List<Character>> {
//                override fun onResponse(
//                    call: Call<List<Character>>,
//                    response: Response<List<Character>>
//                ) {
//                    if (response.body() == null) {
//                        Log.d(TAG, "No characters found!")
//                    } else {
//                        Log.d(TAG, "Characters found: ${response.body()!!.size}")
//
//                        for(c : Character in response.body()!!) {
//                            Log.d(TAG, c.wand.toString())
//
//                            // Add character to database
//                            db.DAO.insert(c)
//                        }
//                    }
//                }
//                override fun onFailure(call: Call<List<Character>>, t: Throwable) {
//                    Log.e(TAG, "Error retrieving list")
//                    t.printStackTrace()
//                }
//            })
        }

    }

}