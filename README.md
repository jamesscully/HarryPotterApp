# Harry Potter App

## Installation 
Clone repository
`git clone https://github.com/jamesscully/HarryPotterApp`

## Features
- Display in Alphabetical order
- Save data to Room database (populates if no database exists)
- Sort by staff/student
- Sort by House

## Dependencies used
As required:
- Retrofit 2
- Kotlin Coroutines

## Devices tested

OnePlus 6 (Android 10) 
Pixel 4 (Emulator, SDK 30)

## Notes
I didn't implement loading from a Room database via secondary source from a Repository, however I have implemented populating the database (as seen in `CharacterDatabase.kt` via `MainActivity.kt`).

I also didn't implement any viewing of character data since this wasn't in the spec, but the entities do contain all data from the API :) 

You can find the Room Database via Database Inspector or 
`/data/data/com.example.harrypottercharacters/databases/characters.db`
after initial launch of the application.