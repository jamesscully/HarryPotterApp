package com.example.harrypottercharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.harrypottercharacters.data.CharacterDatabase
import com.example.harrypottercharacters.data.daos.CharacterDao
import com.example.harrypottercharacters.interfaces.ApiRequest
import com.example.harrypottercharacters.json.CharacterDeserializer
import com.example.harrypottercharacters.models.Character
import com.google.gson.GsonBuilder
import org.w3c.dom.CharacterData
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

	val TAG = "MainActivity"

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val gson = GsonBuilder().registerTypeAdapter(Character::class.java, CharacterDeserializer()).create()

		val retrofit = Retrofit.Builder()
			.baseUrl(Constants.API_URL)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build();

		val api = retrofit.create(ApiRequest::class.java)

		val db = CharacterDatabase.getInstance(this)

		api.getAllCharacters().enqueue(object: Callback<List<Character>> {
			override fun onResponse(
				call: Call<List<Character>>,
				response: Response<List<Character>>
			) {
				if (response.body() == null) {
					Log.d(TAG, "No characters found!")
				} else {
					Log.d(TAG, "Characters found: ${response.body()!!.size}")

					for(c : Character in response.body()!!) {
						Log.d(TAG, c.wand.toString())

						// Add character to database
						db.DAO.insert(c)
					}
				}
			}
			override fun onFailure(call: Call<List<Character>>, t: Throwable) {
				Log.e(TAG, "Error retrieving list")
				t.printStackTrace()
			}
		})

	}
}