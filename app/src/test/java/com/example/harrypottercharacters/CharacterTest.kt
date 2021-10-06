package com.example.harrypottercharacters

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.harrypottercharacters.data.CharacterDatabase
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.manipulation.Ordering

class CharacterTest {

	lateinit var context: Context

	@Before
	fun setup() {
		context = InstrumentationRegistry.getInstrumentation().targetContext
	}

	@Test
	fun testValidity() {
		val db = CharacterDatabase.getInstance(context)

		val character = db.DAO.getByName("Marge Dursley")

		assertTrue(character.alive)

	}
}