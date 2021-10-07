package com.example.harrypottercharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypottercharacters.adapters.CharacterAdapter
import com.example.harrypottercharacters.data.CharacterDatabase
import com.example.harrypottercharacters.models.Character
import com.example.harrypottercharacters.viewmodels.MainActivityViewModel
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {

	val TAG = "MainActivity"

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val model : MainActivityViewModel by viewModels()

		// if we do not have a database, then generate it
		if(!this.applicationContext.getDatabasePath("characters").exists()) {
			CharacterDatabase.populateDatabase(this)
		}

		val spinner = this.findViewById<Spinner>(R.id.characterSortBySpinner)
		val spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_options, R.layout.support_simple_spinner_dropdown_item)
		spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
		spinner.adapter = spinnerAdapter

		val list = emptyList<Character>()
		val recycler = this.findViewById<RecyclerView>(R.id.characterRecyclerView)
		val adapter = CharacterAdapter(list.toMutableList())

		// every time our characters changes, we load a new one into database
		model.characters.observe(this, Observer { characters ->
			adapter.setData(characters)
		})

		recycler.layoutManager = LinearLayoutManager(this)
		recycler.adapter = adapter
	}
}