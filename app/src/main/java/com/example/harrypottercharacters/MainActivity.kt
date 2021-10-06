package com.example.harrypottercharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.harrypottercharacters.interfaces.ApiRequest
import com.example.harrypottercharacters.models.Character
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

	val TAG = "MainActivity"

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val retrofit = Retrofit.Builder()
			.baseUrl(Constants.API_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build();

		val api = retrofit.create(ApiRequest::class.java)

		api.getAllCharacters().enqueue(object: Callback<List<Character>> {
			override fun onResponse(
				call: Call<List<Character>>,
				response: Response<List<Character>>
			) {
				if (response?.body() == null) {
					Log.d(TAG, "No characters found!")
				} else {
					Log.d(TAG, "Characters found: ${response.body()!!.size}")
				}
			}

			override fun onFailure(call: Call<List<Character>>, t: Throwable) {
				TODO("Not yet implemented")
			}

		})


		Log.d(TAG, "Api response: " + api.getAllCharacters())
	}
}