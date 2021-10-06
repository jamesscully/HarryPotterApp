package com.example.harrypottercharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypottercharacters.adapters.CharacterAdapter
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

		val spinner = this.findViewById<Spinner>(R.id.characterSortBySpinner)
		val spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_options, R.layout.support_simple_spinner_dropdown_item)
		spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
		spinner.adapter = spinnerAdapter

		val list = CharacterDatabase.getInstance(this).DAO.getAll()
		val recycler = this.findViewById<RecyclerView>(R.id.characterRecyclerView)
		val adapter = CharacterAdapter(list.toMutableList())

		recycler.layoutManager = LinearLayoutManager(this)
		recycler.adapter = adapter


	}
}