package com.example.harrypottercharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.core.view.children
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypottercharacters.adapters.CharacterAdapter
import com.example.harrypottercharacters.data.CharacterDatabase
import com.example.harrypottercharacters.enums.FilterEnum
import com.example.harrypottercharacters.models.Character
import com.example.harrypottercharacters.viewmodels.MainActivityViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
		val context = applicationContext
		val databaseExists =  getDatabasePath("characters").exists()

		lifecycleScope.launchWhenStarted {
			withContext(Dispatchers.IO) {
				if(!databaseExists)
					CharacterDatabase.populateDatabase(context, (application as App).repo)
			}
		}


		// we can start with an empty list
		val list = emptyList<Character>()
		val recycler = this.findViewById<RecyclerView>(R.id.characterRecyclerView)
		val adapter = CharacterAdapter(list.toMutableList())

		// every time our characters changes, we load a new one into recyclerview
		model.characters.observe(this, Observer { characters ->
			adapter.setData(characters)

			// Update UI with 'Found X results [for] (search type)
			val textView = findViewById<TextView>(R.id.txt_search_info)
			val filter : FilterEnum? = model.filter.value
			var filterText : String = ""

			if(filter != null && filter != FilterEnum.All) {
				filterText = "for $filter"
			}

			textView.text = "Showing ${characters.size} results $filterText"
		})

		recycler.layoutManager = LinearLayoutManager(this)
		recycler.adapter = adapter
	}

	private fun setupChips() {
		val chipGroup = findViewById<ChipGroup>(R.id.filter_chip_group)

		chipGroup.setOnCheckedChangeListener { group, checkedId ->
			// if nothing selected, revert to default 'ALL' and return
			if(group.checkedChipIds.isEmpty()) {
				model.setFilter(FilterEnum.All)
				return@setOnCheckedChangeListener
			}

			val chip = chipGroup.findViewById<Chip>(checkedId)

			// incase we've forgotten a tag on a view
			if(chip.tag == null) {
				Log.e(TAG, "Attempt to retrieve null tag (from chip/filter)")
				return@setOnCheckedChangeListener
			}

			val filter = FilterEnum.valueOf(chip.tag as String)

			model.setFilter(filter)
		}
	}
}