package com.example.harrypottercharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypottercharacters.adapters.CharacterAdapter
import com.example.harrypottercharacters.data.CharacterDatabase
import com.example.harrypottercharacters.enums.FilterEnum
import com.example.harrypottercharacters.models.Character
import com.example.harrypottercharacters.viewmodels.MainActivityViewModel
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {

	val TAG = "MainActivity"

	val model : MainActivityViewModel by viewModels {
		MainActivityViewModel.MainActivityViewModelFactory((application as App).repo)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		setupChips()

		// if we do not have a database, then generate it
		if(!this.applicationContext.getDatabasePath("characters").exists()) {
			CharacterDatabase.populateDatabase(this)
		}

		val spinner = this.findViewById<Spinner>(R.id.characterSortBySpinner)
		val spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_options, R.layout.support_simple_spinner_dropdown_item)
		spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
		spinner.adapter = spinnerAdapter

		// we can start with an empty list
		val list = emptyList<Character>()
		val recycler = this.findViewById<RecyclerView>(R.id.characterRecyclerView)
		val adapter = CharacterAdapter(list.toMutableList())

		// every time our characters changes, we load a new one into recyclerview
		model.characters.observe(this, Observer { characters ->

			// organize list alphabetically; this is potentially an expensive operation,
			// review
			val sorted = characters.sortedWith( compareBy { c -> c.name } )

			Log.d(TAG, "Characters has changed")
			adapter.setData(sorted)
		})

		recycler.layoutManager = LinearLayoutManager(this)
		recycler.adapter = adapter
	}

	private fun generateOnCheckedChangeListener(filter : FilterEnum) : CompoundButton.OnCheckedChangeListener {
		return CompoundButton.OnCheckedChangeListener { _, isChecked ->

			// Chips are bound to a
			if(isChecked)
				model.setFilter(filter)
			else
				model.setFilter(FilterEnum.ALL)
		}
	}


	private fun setupChips() {
		val slytherinChip = findViewById<Chip>(R.id.chip_slytherin)
		val gryffindorChip = findViewById<Chip>(R.id.chip_gryffindor)
		val ravenclawChip = findViewById<Chip>(R.id.chip_ravenclaw)
		val hufflepuffChip = findViewById<Chip>(R.id.chip_hufflepuff)

		val studentChip = findViewById<Chip>(R.id.chip_student)
		val staffChip = findViewById<Chip>(R.id.chip_staff)

		slytherinChip.setOnCheckedChangeListener(generateOnCheckedChangeListener(FilterEnum.SLYTHERIN))
		gryffindorChip.setOnCheckedChangeListener(generateOnCheckedChangeListener(FilterEnum.GRYFFINDOR))
		ravenclawChip.setOnCheckedChangeListener(generateOnCheckedChangeListener(FilterEnum.RAVENCLAW))
		hufflepuffChip.setOnCheckedChangeListener(generateOnCheckedChangeListener(FilterEnum.HUFFLEPUFF))

		studentChip.setOnCheckedChangeListener(generateOnCheckedChangeListener(FilterEnum.STUDENT))
		staffChip.setOnCheckedChangeListener(generateOnCheckedChangeListener(FilterEnum.STAFF))
	}
}